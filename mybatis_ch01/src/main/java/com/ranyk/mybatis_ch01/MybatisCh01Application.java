package com.ranyk.mybatis_ch01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ranyk.mybatis_ch01","com.ranyk.mybatis_ch02", "com.ranyk.mybatis_ch01"})
@MapperScan(basePackages = {"com.ranyk.mybatis_ch01","com.ranyk.mybatis_ch02","com.ranyk.mybatis_ch03"})
public class MybatisCh01Application {

    public static void main(String[] args) {
        SpringApplication.run(MybatisCh01Application.class, args);
    }

}
