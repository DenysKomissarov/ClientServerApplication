package com.example.clientserverapplication.server;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestClass{

    public static boolean isFirstRequestReceived = false;
    @RequestMapping("/request")
    @ResponseBody
    public String getInfo(){

        isFirstRequestReceived = true;
        ServerApplication.tempCountRequest.incrementAndGet();

        return generateString();
    }


    public String generateString() {

        String result = RandomStringUtils.randomAlphabetic(200);
        //System.out.println(Thread.currentThread().getName());
        return  result;
    }
}
