package serein.wanfeng;

import serein.wanfeng.common.URL;
import serein.wanfeng.protocol.HttpServer;
import serein.wanfeng.register.LocalRegister;
import serein.wanfeng.register.RemoteRegister;
import serein.wanfeng.service.ArchiveService;
import serein.wanfeng.serviceimpl.ArchiveServiceImpl;

/**
 * Hello world!
 *
 */
public class ProviderApplication {
    public static void main( String[] args ) {
        //本地注册接口名和实现类Class对象（绑定映射关系）
        LocalRegister.register(ArchiveService.class.getName(), ArchiveServiceImpl.class, "1.0");

        //远程注册
        URL url = new URL("localhost", 8080);
        RemoteRegister.register(ArchiveService.class.getName(), url);

        //启动服务以接收网络请求，即来自consumer模块的远程调用
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);
    }
}
