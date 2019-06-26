package com.djhu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.djhu.bean.TableColumns;
import com.djhu.config.RabbitConfig;
import com.djhu.config.TablesConfig;
import com.djhu.dao.provide.ProvideMapper;
import com.djhu.dao.receive.ReceiveMapper;
import com.djhu.service.ReportSyncService;
import com.djhu.tool.ConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private TableColumns tableColumns;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 获取数据
     *
     * @throws Exception
     */
    @Override
    public void getProvideData() throws Exception {
        //获取同步表过滤字段的最大值（一般选用自增的主键）
        Object primaryVal = receiveMapper.selectMaxPrimary(tablesConfig);
        if (primaryVal != null && !"".equals(primaryVal)) {
            tablesConfig.setPrimaryVal(primaryVal);
        }
        try {
            List dataList = provideMapper.selectProvideData(tablesConfig);
            if (dataList.size() > 0) {
                String data = JSON.toJSONString(dataList);
                CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
                rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTINGKEY, data, correlationId);
            } else {
                logger.debug("当前数据表：{}没有需要同步的数据", tablesConfig.getProvideTableName());
                //默认重新启动任务时间为3分钟
                Thread.sleep(1000 * (tablesConfig.getRestartTime() == null ? 180 : tablesConfig.getRestartTime()));
            }
        } catch (Exception e) {
            logger.error("获取{}的同步数据出现异常：" + e.getMessage(), tablesConfig.getProvideTableName());
            throw e;
        }
    }

    /**
     * 处理数据
     *
     * @param dataMsg rabbitMq消息数据
     * @throws Exception
     */
    @Override
    public void handleReceiveData(String dataMsg) throws Exception {
        List<Object> msgList = JSONObject.parseArray(dataMsg);
        for (Object msg : msgList) {
            logger.info("开始处理数据：" + msg);
            Map<String, Object> paramMap = ConvertUtil.convertMapper(msg, tablesConfig);
            if (paramMap != null) {
                //可能数据重复，避免重复保存（考虑换到redis,避免频繁操作数据库）
                tablesConfig.setPrimaryVal(paramMap.get(tablesConfig.getReceiveKey()));
                int count = receiveMapper.getDataByPrimary(tablesConfig);
                if (count > 0) {
                    logger.info("数据重复：已同步");
                } else {
                    Map<String, String> typeMap = new HashMap<>();
                    //用于声明字段数据类型，解决转换插入报错
                    List<Map<String, Object>> columns = tableColumns.getColumns();
                    String dataType = "";
                    String columnName = "";
                    for (Map<String, Object> column : columns) {
                        dataType = (String) column.get("DATA_TYPE");
                        columnName = (String) column.get("COLUMN_NAME");
                        if (dataType != null && "DATE".equals(dataType)) {
                            ConvertUtil.transMapStringToDate(paramMap, columnName);
                        }
                        typeMap.put(columnName, ConvertUtil.getJdbcType(dataType));
                    }
                    logger.info("开始执行数据插入paramMap：" + paramMap);
                    receiveMapper.insertData(tablesConfig.getReceiveTableName(), paramMap, typeMap);
                }
            }
        }
    }

}
