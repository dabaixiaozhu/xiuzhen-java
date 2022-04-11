package com.jnshu;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDubbo
@SpringBootApplication
@MapperScan("com.jnshu.mapper")
public class WebControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebControllerApplication.class, args);
    }
}
