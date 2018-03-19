package com.example.clientserverapplication.client;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Scheduled(fixedRate = 10000)
    public void executorStop(){
        System.out.println("Executor Stop");
        ClientRestTemplate.executor.shutdownNow();
    }
}
