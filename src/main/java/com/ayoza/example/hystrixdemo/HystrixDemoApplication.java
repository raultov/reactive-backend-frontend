package com.ayoza.example.hystrixdemo;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import rx.Observable;

@SpringBootApplication
@RestController
public class HystrixDemoApplication {

    @GetMapping(value = "/recommended")
    public Observable<String> recommended() throws InterruptedException {
        List<String> randomBooks = new ArrayList<>();
        
        for (int i = 0; i < 20; i++) {
            randomBooks.add("{\"title\": \"" + i + ": " + randomAlphanumeric(20) + "\"}");
        }

        return Observable.from(randomBooks)
                .delay(500, TimeUnit.MILLISECONDS);
        //return Observable.just("{\"title\": \"un buen libro\"}");
        //return "{\"title\": \"un buen libraco\"}";
    }
    
	public static void main(String[] args) {
		SpringApplication.run(HystrixDemoApplication.class, args);
	}
}
