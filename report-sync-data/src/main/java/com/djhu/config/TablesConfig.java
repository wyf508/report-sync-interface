package com.djhu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @Author yf.wu
 * @Date 2019-06-23
 * @Description 表字段、映射关系、查询属性和条件的配置（详细说明见配置文件）
 */
@ConfigurationProperties(prefix = "tables")
@Component
@Scope("prototype")
@Validated
@Data
public class TablesConfig {

    @NotNull
    private String provideTableName;

    @NotNull
    private String receiveTableName;

    @NotNull
    private String provideKey;

    @NotNull
    private String receiveKey;

    private String condition;

    @Min(value=0)
    @Max(value = 500)
    private Integer pageSize;

    private Object primaryVal;

    @Min(value=0)
    private Integer restartTime;

    private Map<String,Object> defaultVal;

    @NotNull
    private Map<String, String> fieldMapping;


}
