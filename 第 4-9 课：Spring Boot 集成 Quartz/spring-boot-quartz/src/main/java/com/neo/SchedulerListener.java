package com.neo;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
@Component
public class SchedulerListener {  
      
//    @Scheduled(cron="0 30 11 25 11 ?")
    @Scheduled(fixedDelay = 1000)
    public void schedule() {
        System.out.println("hhh");
     }   
      
}