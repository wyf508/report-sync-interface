package com.djhu.bean;

import java.util.List;
import java.util.Map;

/**
 * @author yf.wu
 * @date 2019-06-25-10:58
 * @Description 表字段属性
 */
public class TableColumns {

    private String tableName;
    private List<Map<String, Object>> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Map<String, Object>> getColumns() {
        return columns;
    }

    public void setColumns(List<Map<String, Object>> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "TableColumns{" +
                "tableName='" + tableName + '\'' +
                ", columns=" + columns +
                '}';
    }
}
