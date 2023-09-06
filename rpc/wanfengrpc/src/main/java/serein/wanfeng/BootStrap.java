package serein.wanfeng;

import serein.wanfeng.common.URL;
import serein.wanfeng.protocol.HttpServer;
import serein.wanfeng.register.LocalRegister;
import serein.wanfeng.register.RemoteRegister;
import serein.wanfeng.service.ArchiveService;

/**
 * @Date: 2023-09-06 19:24
 * @Author: luozh
 * @Description:
 */

public class BootStrap {

    public static void start(){
        //远程注册
        URL url = new URL("localhost", 8080);
        RemoteRegister.register(ArchiveService.class.getName(), url);

        //启动服务以接收网络请求，即来自consumer模块的远程调用
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);
    }
}
