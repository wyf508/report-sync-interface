package com.djhu.job;

import com.djhu.service.ReportSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @Author yf.wu
 * @Date 2019-06-23
 * @Description 数据同步总调度任务
 */
@Component
public class ReportSyncJob {

    private static final Logger log = LoggerFactory.getLogger(ReportSyncJob.class);

    @Value("${sync.getProvideData.start}")
    private boolean getProvideDataStart;

    private boolean firstProcess = true;

    @Autowired
    private ReportSyncService reportSyncService;

    @Scheduled(cron = "${sync.getProvideData.cron}")
    public void getProvideData() {
        try {
            if (!getProvideDataStart) {
                if (firstProcess) {
                    log.error("数据同步任务未启动，如需启动请修改配置文件sync.getProvideData.start=true");
                    firstProcess = false;
                }
                return;
            }
            log.info("数据同步任务启动...");
            reportSyncService.getProvideData();
        } catch (Exception e) {
            log.error("数据同步任务发生异常：" + e.getMessage(), e);
        }
    }

}
