package com.example.club;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.club.mapper")
public class ClubBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClubBackendApplication.class, args);
    }

}
