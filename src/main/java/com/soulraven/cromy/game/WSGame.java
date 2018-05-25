package com.soulraven.cromy.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath*:application-config.xml")
@SpringBootApplication
public class WSGame {

    public static void main(String[] args) {
        SpringApplication.run(WSGame.class, args);
    }
}
