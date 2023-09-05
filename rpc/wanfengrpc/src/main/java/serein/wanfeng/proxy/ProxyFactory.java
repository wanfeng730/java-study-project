package serein.wanfeng.proxy;

import serein.wanfeng.common.Invocation;
import serein.wanfeng.common.URL;
import serein.wanfeng.entity.Archive;
import serein.wanfeng.loadbalance.LoadBalanceStrategy;
import serein.wanfeng.protocol.HttpClient;
import serein.wanfeng.register.RemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @Date: 2023-09-05 20:09
 * @Author: luozh
 * @Description:
 */

public class ProxyFactory {
    public static <T> T getProxy(Class interfaceClass){
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);

                //服务发现（获取接口名对应的url）
                //这里的Register与Provider应用不属于同一个进程，故会报NullException，此处是为了模拟远程服务注册和服务发现
                //使用文件来存放注册中心的map，来代替远程
                List<URL> urls = RemoteRegister.get(interfaceClass.getName());

                //负载均衡
                URL url = LoadBalanceStrategy.random(urls);

                HttpClient httpClient = new HttpClient();
                return httpClient.send(url.getHostname(), url.getPort(), invocation);
            }
        });

        return (T) proxyInstance;
    }
}
