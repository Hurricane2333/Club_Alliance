package com.admin.club;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.admin.club.mapper")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.admin.club.AdminApplication.class, args);
    }

}
