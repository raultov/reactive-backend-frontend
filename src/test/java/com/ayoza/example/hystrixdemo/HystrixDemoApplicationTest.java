package com.ayoza.example.hystrixdemo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableFeignClients
public class HystrixDemoApplicationTest {

    @Autowired
    private CommandClient client;

    @Test
    public void hystrixTesting() {
        String result = null;
        try {
            result = client.getRecommended().toBlocking().first();
        } catch(Exception e) {
            System.out.println(e);
        }
        
        assertThat(result).isNull();
    }
}
