package com.boot.yuncourier.task;

import com.boot.yuncourier.entity.system.Performance;
import com.boot.yuncourier.service.system.PerformanceService;
import com.boot.yuncourier.util.WindowsInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: skwen
 * @Description: MultithreadScheduleTask-多線程任務
 * @Date: 2020-02-01
 */

@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
public class MultithreadScheduleTask {
    @Autowired
    private WindowsInfoUtil WindowsInfoUtil;
    @Autowired
    private PerformanceService performanceService;

    @Async
    @Scheduled(fixedDelay = 1000*60*5)
    public void addPerformanceByPerformance() {
        Performance performance = new Performance();
        performance.setCpu(WindowsInfoUtil.getCpu());
        performance.setRam(WindowsInfoUtil.getRam());
        performanceService.addPerformanceByPerformance(performance);
    }
//    @Async
//    @Scheduled(fixedDelay = 2000)
//    public void second() {
//        System.out.println("第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
//        System.out.println();
//    }
}