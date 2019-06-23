package com.djhu.dao.provide;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: yf.wu
 * @createdate: 2019-06-20 23:15
 * @description: 跨系统数据同步provide的通用mapper
 **/
@Mapper
public interface ProvideMapper<T> {

    /**
     * 根据动态查询条件获取需要同步的数据
     * @param param
     * @return
     */
    List<Map<String, Object>> selectProvideData(@Param(value = "param") Map<String, Object> param);

}
