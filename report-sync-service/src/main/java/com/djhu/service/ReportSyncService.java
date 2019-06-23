package com.djhu.service;

import java.util.List;

/**
 * * 系统获取数据任务：
 * *  1.需要根据配置（receive和provide对应的表字段映射关系），找到receive过滤字段的primaryKey（一般选用主键）的最大值
 * *  2.在provide根据过滤的字段和其他条件，查询出需要同步的数据集
 * *  3.拿到数据集后，存入mq消息队列待消费
 * * 系统消费数据：
 * *  4.系统监听消息队列，根据字段映射关系，保存数据到receive表上
 *
 * @Author yf.wu
 * @Date 2019-06-23
 * @Description 统一的文书数据同步接口
 * @Process 根据系统映射配置文件，指定需要同步的provide表和保存数据的receive表
 */
public interface ReportSyncService {

    /**
     *
     * @return 过滤条件字段最大值
     */
    Object getReceiveKey();


    /**
     *
     * @return provide同步数据集获取
     * @throws Exception
     */
    void getProvideData() throws Exception;

    /**
     *
     * @return 同步数据保存
     */
    int insertData();


}
