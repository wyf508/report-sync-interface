package com.djhu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Map;
import java.util.Set;

/**
 * @Author yf.wu
 * @Date 2019-06-23
 * @Description 表字段、映射关系、查询属性和条件的配置
 */
@ConfigurationProperties(prefix = "tables")
@Validated
@Component
@Scope("prototype")
public class TablesConfig {

    private String provideTableName;

    private String receiveTableName;

    private String provideKey;

    private String receiveKey;

    private String condition;

    private Integer pageSize;

    private Set<Map<String, Object>> fields;

    public String getProvideTableName() {
        return provideTableName;
    }

    public void setProvideTableName(String provideTableName) {
        this.provideTableName = provideTableName;
    }

    public String getReceiveTableName() {
        return receiveTableName;
    }

    public void setReceiveTableName(String receiveTableName) {
        this.receiveTableName = receiveTableName;
    }

    public String getProvideKey() {
        return provideKey;
    }

    public void setProvideKey(String provideKey) {
        this.provideKey = provideKey;
    }

    public String getReceiveKey() {
        return receiveKey;
    }

    public void setReceiveKey(String receiveKey) {
        this.receiveKey = receiveKey;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Set<Map<String, Object>> getFields() {
        return fields;
    }

    public void setFields(Set<Map<String, Object>> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "TablesConfig{" +
                "provideTableName='" + provideTableName + '\'' +
                ", receiveTableName='" + receiveTableName + '\'' +
                ", provideKey='" + provideKey + '\'' +
                ", receiveKey='" + receiveKey + '\'' +
                ", condition='" + condition + '\'' +
                ", pageSize=" + pageSize +
                ", fields=" + fields +
                '}';
    }
}
