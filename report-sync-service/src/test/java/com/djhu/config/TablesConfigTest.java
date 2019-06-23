package com.djhu.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

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

    @Test
    public void testConfig() {
        System.out.println("-----tablesConfig------" + tablesConfig);
    }
}