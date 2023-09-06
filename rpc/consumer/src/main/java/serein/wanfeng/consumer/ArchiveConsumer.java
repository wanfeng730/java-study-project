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
        //动态代理优化
        ArchiveService archiveService = ProxyFactory.getProxy(ArchiveService.class);
        Archive archive1 = archiveService.createArchive("杭州亚运会建设报告");
        System.out.println(archive1);
    }
}
