package com.souls;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.souls.mapper")
public class StudentManageSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManageSpringBootApplication.class, args);
    }

}
