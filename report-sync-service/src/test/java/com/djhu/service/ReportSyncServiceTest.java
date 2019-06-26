package com.djhu.service;

import com.djhu.config.TablesConfig;
import com.djhu.dao.provide.ProvideMapper;
import com.djhu.dao.receive.ReceiveMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author yf.wu
 * @date 2019-06-24-10:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportSyncServiceTest {

    @Autowired
    private ReportSyncService reportSyncService;
    @Autowired
    private ProvideMapper provideMapper;
    @Autowired
    private ReceiveMapper receiveMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private TablesConfig tablesConfig;

    @Test
    public void getProvideData() {
        try {
            reportSyncService.getProvideData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}