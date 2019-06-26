package com.djhu.dao;

import com.djhu.BaseTest;
import com.djhu.config.TablesConfig;
import com.djhu.dao.receive.ReceiveMapper;
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
public class ReceiveMapperTest extends BaseTest {

    @Autowired
    private ReceiveMapper receiveMapper;
    @Autowired
    private TablesConfig tablesConfig;

    @Test
    public void selectMaxPrimary() {
//        System.out.println("tablesConfig:"+tablesConfig);
//        String primary = receiveMapper.selectMaxPrimary(tablesConfig);
//        System.out.println("primary:" + primary);
    }

    @Test
    public void insertData() {
//            employee = receiveMapper.insertData("EMPLOYEE", params);
    }
}