package com.example.back2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync //开启异步任务
@SpringBootApplication
public class Back2Application {

    public static void main(String[] args) {
        SpringApplication.run(Back2Application.class, args);
    }

}
