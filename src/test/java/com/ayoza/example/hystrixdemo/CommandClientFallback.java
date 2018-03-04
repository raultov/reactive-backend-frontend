package com.ayoza.example.hystrixdemo;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;
import rx.Observable;

@Component
public class CommandClientFallback implements FallbackFactory<CommandClient> {
    
    @Override
    public CommandClient create(Throwable cause) {
        return new CommandClient() {
            @Override
            public Observable<String> getRecommended() {
               // return "fallback; reason was: " + cause.getMessage();
                return null;
            }
        };
    }
}
