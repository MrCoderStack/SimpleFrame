package com.mrcoder.frameservice.schedule;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @Description: 定时任务类
 * @author: mrcoder
 */
@Component
@Slf4j
public class ScheduleJob {

    private static final long ONE_MINUTE = 60 * 1000;

    private static final long TWO_MINUTE = 60 * 1000 * 2;

    private static final long THREE_MINUTE = 60 * 1000 * 3;

    private static final long TEN_MINUTE = 60 * 1000 * 10;

    /**
     * 定时任务测试
     */
    @Async("scheduleThreadPoolTask")
    @Scheduled(initialDelay = 8000, fixedDelay = ONE_MINUTE)
    @SchedulerLock(name = "autoPrintTestLog", lockAtMostFor = ONE_MINUTE, lockAtLeastFor = ONE_MINUTE)
    public void autoPrintTestLog() {
        log.info("autoPrintTestLog  ==>  定时任务测试 ");
    }
}
