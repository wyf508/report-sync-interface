package com.djhu.dao.receive;

import com.djhu.config.TablesConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: yf.wu
 * @createdate: 2019-06-20 23:15
 * @description: 跨系统数据同步receive的通用mapper
 */


@Mapper
public interface ReceiveMapper {

    //查出receive表主键的最大值
    Object selectMaxPrimary(@Param("params") TablesConfig tablesConfig);

    //动态插入数据
    long insertData(@Param(value = "receiveTableName") String tableName, @Param(value = "params") Map<String, Object> params, @Param(value = "typeParam") Map<String, String> typeParam);

    //数据去重查询
    int getDataByPrimary(@Param("params") TablesConfig tablesConfig);

    //初始化获取表字段结构
    List<Map<String, Object>> getReceiveTableColumns(@Param("receiveTableName") String receiveTableName);


}
