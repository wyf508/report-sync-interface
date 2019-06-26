package com.djhu.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author yf.wu
 * @Date 2019-06-25-10:38
 * @Description 同步前初始化检查，数据准备等
 */
@Component
public class ReportSyncInitial implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        logger.info("服务启动，开始执行初始化配置和检查...");

    }

}
