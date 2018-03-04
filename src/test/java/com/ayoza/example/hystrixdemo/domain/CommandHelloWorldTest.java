package com.ayoza.example.hystrixdemo.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CommandHelloWorldTest {

    @Test
    public void givenInputBobAndDefaultSettings_whenCommandExecuted_thenReturnHelloBob(){
        assertThat(new CommandHelloWorld("Bob").execute()).isEqualTo("Hello Bob!");
    }
}
