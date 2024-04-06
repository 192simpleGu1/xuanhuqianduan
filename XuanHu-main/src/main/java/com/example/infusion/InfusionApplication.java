package com.example.infusion;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.infusion.mapper")
public class InfusionApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfusionApplication.class, args);
    }

}
