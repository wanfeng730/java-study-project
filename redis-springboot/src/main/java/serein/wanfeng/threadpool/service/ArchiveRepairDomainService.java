package serein.wanfeng.threadpool.service;

import lombok.Data;
import lombok.Synchronized;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import serein.wanfeng.entity.Archive;
import serein.wanfeng.threadpool.config.LanxiRepairTaskExecutorConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @date: 2023-12-12 16:33
 * @author: luozh
 * @description:
 * @since:
 */
@Service
public class ArchiveRepairDomainService {

    public static final String LANXI_REPAIR_LOG_PREFIX = "【兰溪档案库修复】";
    private static final String REPAIR_FLAG = "da_repair_flag";

    /**
     * 兰溪档案修复具体业务
     */
    @Async
    public void repairLanxiArchive(List<Archive> saveList, Integer count, AtomicReference<Long> successCount, CountDownLatch latch){
        long sc = 0L;
        try {
            // LzhTODO: CountDownLatch 同步线程的返回结果
            for (Integer i = 0; i < count; i++) {
                syncAddSaveObject(saveList, new Archive("ARC"+i, Thread.currentThread().getName(), null));
                System.out.println(LANXI_REPAIR_LOG_PREFIX + Thread.currentThread().getName() + "  count: " + i);
                sc++;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            syncIncrSuccessCount(successCount, sc);
            latch.countDown();
        }
    }

    @Synchronized
    public void syncAddSaveObject(List<Archive> saveObjectList, Archive object){
        saveObjectList.add(object);
    }

    @Synchronized
    public void syncIncrSuccessCount(AtomicReference<Long> successCount, long incrNum){
        successCount.set(successCount.get() + incrNum);
    }

    @Synchronized
    public void syncIncrFailedCount(AtomicReference<Long> failedCount, long incrNum){
        failedCount.set(failedCount.get() + incrNum);
    }

    /**
     * 异步执行结果汇总
     */
    @Data
    public class AsyncRepairResult{
        private List<Archive> saveObjectList = new ArrayList<>();

        private long successCount;
        private long failedCount;

        public void addSaveObject(Archive object){
            this.saveObjectList.add(object);
        }
    }

    public static ArchiveRepairDomainService getAsyncTaskRepairDomainService(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LanxiRepairTaskExecutorConfig.class);
        ArchiveRepairDomainService service = context.getBean(ArchiveRepairDomainService.class);
        return service;
    }
}
