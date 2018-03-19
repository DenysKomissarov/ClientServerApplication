package com.example.clientserverapplication.client;

import com.example.clientserverapplication.server.ServerApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sun.applet.Main;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.logging.Logger;


public class ClientRestTemplate {

    private static final Logger logger = Logger.getLogger(ClientRestTemplate.class.getName());
    public static final String URL = "http://localhost:8080/request";
    public static final ExecutorService executor = Executors.newCachedThreadPool();
    //public static final ExecutorService executor2 = Executors.newFixedThreadPool(1);
    public static boolean isExecutorStoped = false;

    public static void main(String[] args) {

        for (int i = 0; i < 200; i ++ ){
            ReadWriterThread readWriterThread = new ReadWriterThread(i);
            executor.submit(readWriterThread);
        }

        try {
//            System.out.println("attempt to shutdown executor");
//            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {

            System.err.println("tasks interrupted");
        }
        finally {
            isExecutorStoped = true;
            executor.shutdownNow();
            while (!executor.isTerminated()){
                logger.info("tasks interrupted");
            }
            logger.info("shutdown finished");
        }
    }
}
