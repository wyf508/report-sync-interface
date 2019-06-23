package com.djhu.service.impl;

import com.djhu.config.RabbitConfig;
import com.djhu.config.TablesConfig;
import com.djhu.dao.provide.ProvideMapper;
import com.djhu.dao.receive.ReceiveMapper;
import com.djhu.service.ReportSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author yf.wu
 * @Date 2019-06-23
 * @Description TODO
 */
@Service
public class ReportSyncServiceImpl implements ReportSyncService {

    private final Logger logger = LoggerFactory.getLogger(ReportSyncServiceImpl.class);

    @Autowired
    private ProvideMapper provideMapper;
    @Autowired
    private ReceiveMapper receiveMapper;
    @Autowired
    private TablesConfig tablesConfig;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Object getReceiveKey() {
        Object primary = receiveMapper.selectMaxPrimary(tablesConfig.getReceiveTableName(), tablesConfig.getReceiveKey());
        return primary;
    }

    @Override
    public int insertData() {

        Map<String,Object> map = new HashMap<>();
        receiveMapper.insertData(tablesConfig.getReceiveTableName(),map);
        return 0;
    }

    @Override
    public void getProvideData() throws Exception {
        /*Map<String, Object> map = new HashMap<>();
        map.put("tableName", "CT_LINKCOMP");
        map.put("sourceKey", "id");
        map.put("targetKeyVal", 200);
        map.put("condition", "CELLTREEID > 14000 ");
        map.put("pageSize", 10);*/
        Map<String, Object> map = new HashMap<>();
        List list = provideMapper.selectProvideData(map);
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTINGKEY, list, correlationId);

    }


}
