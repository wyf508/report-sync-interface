package com.djhu.config;

import com.djhu.bean.TableColumns;
import com.djhu.dao.receive.ReceiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author yf.wu
 * @date 2019-06-25-15:48
 */
@Configuration
public class TableColumnsConfig {

    @Autowired
    private ReceiveMapper receiveMapper;
    @Autowired
    private TablesConfig tablesConfig;

    /**
     * 获取Receive表的字段结构
     */
    @Bean("tableColumns")
    public TableColumns getReceiveTableColumns() {
        List<Map<String, Object>> tableColumnsList = receiveMapper.getReceiveTableColumns(tablesConfig.getReceiveTableName());
        TableColumns tableColumns = new TableColumns();
        tableColumns.setTableName(tablesConfig.getReceiveTableName());
        tableColumns.setColumns(tableColumnsList);
        return tableColumns;
    }

}
