package com.djhu.dao.provide;

import com.djhu.config.TablesConfig;
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
     *
     * @return 需要同步的数据集
     */
    List<Map<String, Object>> selectProvideData(@Param("params") TablesConfig tablesConfig);


}
