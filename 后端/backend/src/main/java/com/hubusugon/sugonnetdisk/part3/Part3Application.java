package com.hubusugon.sugonnetdisk.part3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hubusugon.sugonnetdisk.part3.mapper")
public class Part3Application {

    public static void main(String[] args) {
        SpringApplication.run(Part3Application.class, args);
    }

}
