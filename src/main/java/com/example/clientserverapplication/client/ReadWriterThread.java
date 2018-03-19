package com.example.clientserverapplication.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadWriterThread implements Runnable {
    private  static final Logger loger =  Logger.getLogger(ReadWriterThread.class.getName());
    private RestTemplate restTemplate;
    private int num;

    public ReadWriterThread(int num) {
        this.restTemplate = new RestTemplate();
        this.num = num;
    }

    @Override
    public void run() {

        int i = 0;
        while(i < 10000){

            if(ClientRestTemplate.isExecutorStoped)
                break;

            try {
                ResponseEntity<String> infoResponce = restTemplate.getForEntity(ClientRestTemplate.URL,String.class);
                System.out.println("Thread " + num + " :  " + infoResponce.getBody());//
                i++;
            }catch(ResourceAccessException ex){
                loger.info( ex.getMessage());
            }


        }

    }
}
