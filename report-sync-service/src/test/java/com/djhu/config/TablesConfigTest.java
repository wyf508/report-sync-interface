package com.djhu.config;

import com.djhu.bean.TableColumns;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author yf.wu
 * @Date 2019-06-23
 * @Description TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TablesConfigTest {

    @Autowired
    private TablesConfig tablesConfig;
    @Autowired
    private TableColumns tableColumns;

    @Test
    public void testConfig() {
        System.out.println("测试系统配置tablesConfig:  " + tablesConfig);
        System.out.println("测试系统配置tableColumns:  " + tableColumns);
    }
}