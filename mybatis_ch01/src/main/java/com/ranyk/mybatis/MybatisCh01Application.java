package com.ranyk.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ranyi
 */
@SpringBootApplication(scanBasePackages = {"com.ranyk.mybatis"})
@MapperScan(basePackages = {"com.ranyk.mybatis"})
@EnableScheduling
public class MybatisCh01Application {

    public static void main(String[] args) {
        SpringApplication.run(MybatisCh01Application.class, args);
    }

}
