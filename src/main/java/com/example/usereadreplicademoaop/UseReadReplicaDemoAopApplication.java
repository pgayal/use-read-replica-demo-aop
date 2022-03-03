package com.example.usereadreplicademoaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.usereadreplicademoaop.model")
public class UseReadReplicaDemoAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(UseReadReplicaDemoAopApplication.class, args);
    }

}
