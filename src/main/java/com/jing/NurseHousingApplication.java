package com.jing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@MapperScan("com.jing.mapper")
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled=true)
public class NurseHousingApplication {

    public static void main(String[] args) {
        SpringApplication.run(NurseHousingApplication.class, args);
    }

}
