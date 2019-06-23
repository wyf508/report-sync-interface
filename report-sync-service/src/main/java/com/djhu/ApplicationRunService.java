package com.djhu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Creator yf.wu
 * @CreateDate 2019/5/25 0025 下午 4:48
 * @Description SpringBoot启动类
 */
@SpringBootApplication
public class ApplicationRunService {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunData.class, args);
    }

}
