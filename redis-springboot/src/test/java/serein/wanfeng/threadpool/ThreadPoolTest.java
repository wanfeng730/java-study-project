package serein.wanfeng.threadpool;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import serein.wanfeng.entity.Archive;
import serein.wanfeng.threadpool.service.ArchiveRepairDomainService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @date: 2023-12-12 17:19
 * @author: luozh
 * @description:
 * @since:
 */
@SpringBootTest
public class ThreadPoolTest {

    @Test
    public void testThreadRun() throws InterruptedException, ExecutionException {
        ArchiveRepairDomainService repairDomainService = ArchiveRepairDomainService.getAsyncTaskRepairDomainService();
        AtomicReference<Long> successCount = new AtomicReference<>(0L);
        List<Archive> saveList = new ArrayList<>();

        CountDownLatch latch = new CountDownLatch(3);
        for(int i=0; i<3; i++){
            repairDomainService.repairLanxiArchive(saveList, 100, successCount, latch);
        }
        latch.await();

        System.out.println(successCount);
    }
}
