package com.ayoza.example.hystrixdemo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rx.Observable;

@FeignClient(value = "commandClient", url = "http://localhost:8080")//, fallbackFactory = CommandClientFallback.class)
public interface CommandClient {

    @RequestMapping(method = RequestMethod.GET, value = "/recommended")
    Observable<String> getRecommended();
    
}
