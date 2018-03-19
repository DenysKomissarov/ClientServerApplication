package com.example.clientserverapplication.server;

//import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.apache.commons.io.FileUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SheduledStatistic {

    private static final Logger logger = Logger.getLogger(SheduledStatistic.class.getName());
    private int countRequest = 0;
    private int countSecond = 0;

    @Scheduled(fixedRate = 1000)
    private void logStatistic(){
        if (RestClass.isFirstRequestReceived){

                logger.log(Level.INFO,"Count of request in one second " + String.valueOf(ServerApplication.tempCountRequest));
                this.countRequest += ServerApplication.tempCountRequest.get();
                this.countSecond ++;
                //logger.log(Level.INFO, "Average count of requests in one second: "+(countRequest/countSecond));
                ServerApplication.tempCountRequest.set(0);

                try {
                    FileUtils.writeStringToFile(new File("target/loog_info.txt"), "Average count of requests: " +
                            (countRequest/countSecond) + "\n", "UTF-8",true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }
}
