package serein.wanfeng.threadpool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @date: 2023-12-12 16:28
 * @author: luozh
 * @description:
 * @since:
 */

@Configuration
@ComponentScan("serein.wanfeng.threadpool")
@EnableAsync
public class LanxiRepairTaskExecutorConfig implements AsyncConfigurer {
    /**
     * 线程池最大容量
     * IO密集型（磁盘、网络交互所需时间大于CPU计算）
     * CPU密集型（CPU计算所需时间大于IO）
     */
    public static final int THREAD_MAX_CAPACITY = 2 * (Runtime.getRuntime().availableProcessors());

    /**
     * 队列数量
     */
    private static final int QUEUE_CAPACITY = 1000;

    @Override
    @Bean
    public Executor getAsyncExecutor() {
        //获取当前服务器的核数
        int processorNum = Runtime.getRuntime().availableProcessors();

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(THREAD_MAX_CAPACITY);
        taskExecutor.setMaxPoolSize(THREAD_MAX_CAPACITY);
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
