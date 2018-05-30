package com.soulraven.cromy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration
public class WSGame {

    public static void main(String[] args) {
        SpringApplication.run(WSGame.class, args);
    }
}
