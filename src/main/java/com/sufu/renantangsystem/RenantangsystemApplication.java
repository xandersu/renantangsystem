package com.sufu.renantangsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.sufu.renantangsystem.repository")
public class RenantangsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RenantangsystemApplication.class, args);
    }


}
