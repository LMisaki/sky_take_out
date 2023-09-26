package com.sky.task;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Desc: 自定义定时任务类
 * @return {@link null}
 * @author L_Misaki
 */
@Component
@Slf4j
public class MyTask {

    //@Scheduled(cron = "0/5 * * * * ?")
    public void excuteTask(){
        log.info("定时任务开始执行：{}",new Date());
    }
}
