package com.djhu.tool;

import com.djhu.config.TablesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author yf.wu
 * @Date 2019-06-23
 * @Description 数据转换工具类
 */
public class ConvertUtil {

    private static final Logger logger = LoggerFactory.getLogger(ConvertUtil.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * map日期字符串转日期格式
     *
     * @param map
     * @param dateKey
     */
    public static void transMapStringToDate(Map<String, Object> map, String dateKey) {
        Object obj = map.get(dateKey);
        try {
            if (obj instanceof String) {
                map.put(dateKey, sdf.parse(obj.toString()));
            } else if (obj instanceof Long) {
                map.put(dateKey, new Date((Long) obj));
            } else {
                logger.error("待转换的数据格式：{},{}", obj, obj.getClass().getName());
            }
        } catch (Exception e) {
            logger.error("dateKey：{}数据转换日期格式异常：" + e.getMessage(), dateKey);
        }
    }

    /**
     * 根据配置映射关系，转换消息，保存数据
     * map(provideKey,val) ---> map(receiveKey,val)
     *
     * @param msg 消息内容
     * @return 转换后保存的数据内容
     */
    public static Map<String, Object> convertMapper(Object msg, TablesConfig tablesConfig) {
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> msgMap = (Map<String, Object>) msg;
        tablesConfig.getFieldMapping().forEach((key, val) -> {
            paramMap.put(val, msgMap.get(key));
        });
        //插入其他默认属性值
        Map<String, Object> defaultVal = tablesConfig.getDefaultVal();
        if (defaultVal != null) {
            defaultVal.forEach((key, value) -> {
                paramMap.put(key, value);
            });
        }
        return paramMap;
    }


    /**
     * JdbcType转换
     *
     * @param dataType
     * @return
     */
    public static String getJdbcType(String dataType) {
        if ("".equals(dataType)) return "NULL";
        else if ("VARCHAR2".equals(dataType)) return "VARCHAR";
        else if ("NUMBER".equals(dataType)) return "NUMERIC";
        else if ("DATE".equals(dataType)) return "TIMESTAMP";
        else if ("CLOB".equals(dataType)) return "CLOB";
        else if ("CHAR".equals(dataType)) return "CHAR";
        else if ("NVARCHAR2".equals(dataType)) return "NVARCHAR";
        else if (dataType.indexOf("TIMESTAMP") > -1) return "TIMESTAMP";
        else return "VARCHAR";
    }
}
