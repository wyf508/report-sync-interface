package com.djhu.dao.receive;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author: yf.wu
 * @createdate: 2019-06-20 23:15
 * @description: 跨系统数据同步receive的通用mapper
 */


@Mapper
public interface ReceiveMapper {

    //查出receive表主键的最大值
    String selectMaxPrimary(@Param(value = "receiveTableName") String tableName, @Param(value = "receiveKey") String primaryKey);


    //动态插入数据
    long insertData(@Param(value = "receiveTableName") String tableName, @Param(value = "params") Map<String, Object> param);

    /**
     * 查：（根据自定义的配置映射文件）
     * 1.先查本地库是否已经建好数据表
     * 2.如果未建表，系统自动按配置规则建表
     *   如果已经建好表，
     */


    //查出目标表信息

    //查出本地


}
