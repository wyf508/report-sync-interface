package com.djhu;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Creator yf.wu
 * @CreateDate 2019/5/25 0025 下午 4:48
 * @Description SpringBoot启动类
 */
@SpringBootApplication
@EnableRabbit
@EnableScheduling
public class ApplicationRunService {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunService.class, args);
    }

}
