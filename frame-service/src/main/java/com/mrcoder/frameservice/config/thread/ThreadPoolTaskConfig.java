package com.mrcoder.frameservice.config.thread;


import java.util.concurrent.ThreadPoolExecutor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 定时任务线程池配置
 *
 * @author mrcoder
 */
@Configuration
@EnableAsync
@Slf4j
public class ThreadPoolTaskConfig {

    /**
     * 允许线程空闲时间（单位：默认为秒）
     */
    private static final int keepAliveTime = 10;
    /**
     * 缓冲队列大小
     */
    private static final int queueCapacity = 100;
    /**
     * 线程池名前缀
     */
    private static final String threadNamePrefix = "schedule-thread-";

    @Bean("scheduleThreadPoolTask")
    public ThreadPoolTaskExecutor taskExecutor() {
        log.info("scheduleThreadPoolTask init");
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        int core = Runtime.getRuntime().availableProcessors();
        log.info("scheduleThreadPoolTask core:" + core);
        //设置核心线程数
        pool.setCorePoolSize(core);
        //设置最大线程数
        pool.setMaxPoolSize(core * 2 + 1);
        pool.setQueueCapacity(queueCapacity);
        pool.setKeepAliveSeconds(keepAliveTime);
        pool.setThreadNamePrefix(threadNamePrefix);
        // 线程池对拒绝任务的处理策略 CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        pool.initialize();
        return pool;
    }
}
