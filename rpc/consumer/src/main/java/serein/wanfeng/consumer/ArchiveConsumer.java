package serein.wanfeng.consumer;

import serein.wanfeng.common.Invocation;
import serein.wanfeng.entity.Archive;
import serein.wanfeng.protocol.HttpClient;
import serein.wanfeng.proxy.ProxyFactory;
import serein.wanfeng.service.ArchiveService;

/**
 * @Date: 2023-09-05 16:24
 * @Author: luozh
 * @Description: rpc消费者
 */

public class ArchiveConsumer {
    public static void main(String[] args) {
        //调用公共接口ArchiveService中的方法即可执行实现类的方法体
        Invocation invocation = new Invocation(ArchiveService.class.getName(), "createArchive",
                new Class[]{String.class}, new Object[]{"杭州亚运会奖牌设计"});

        HttpClient httpClient = new HttpClient();
        Archive archive = httpClient.send("localhost", 8080, invocation);
        System.out.println(archive);

        //动态代理优化
        ArchiveService archiveService = ProxyFactory.getProxy(ArchiveService.class);
        Archive archive1 = archiveService.createArchive("杭州亚运会建设报告");
        System.out.println(archive1);
    }
}
