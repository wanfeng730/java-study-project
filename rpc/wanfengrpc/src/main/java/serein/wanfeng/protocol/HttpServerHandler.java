package serein.wanfeng.protocol;

import org.apache.commons.io.IOUtils;
import serein.wanfeng.common.Invocation;
import serein.wanfeng.entity.Archive;
import serein.wanfeng.register.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;

/**
 * @Date: 2023-09-05 16:58
 * @Author: luozh
 * @Description: 网络请求业务处理层
 */

public class HttpServerHandler {

    public void handle(HttpServletRequest req, HttpServletResponse resp){
        //业务处理：拿到Invocation对象获取接口、方法、参数
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            String interfaceName = invocation.getInterfaceName();
            //实现类
            Class implClass = LocalRegister.get(interfaceName, "1.0");
            //根据方法名和参数类型列表获取实现类中的方法
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            //调用方法，接收返回值
            Archive archive = (Archive) method.invoke(implClass.newInstance(), invocation.getParameters());

            //对象转为byte数组并写入响应体的输出流
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(archive);
            IOUtils.write(byteArrayOutputStream.toByteArray(), resp.getOutputStream());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
