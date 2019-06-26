package com.djhu.service;

import java.util.List;
import java.util.Map;

/**
 * @Author yf.wu
 * @Date 2019-06-23
 * @Description 统一的文书数据同步接口服务
 * @Process 根据系统映射配置文件，指定需要同步的provide表和保存数据的receive表
 */
public interface ReportSyncService {

    /**
     * 获取同步数据
     */
    void getProvideData() throws Exception;

    /**
     * 处理/保存接收的数据
     */
    void handleReceiveData(String dataMsg) throws Exception;


}
