package com.djhu.dao;

import com.djhu.dao.receive.ReceiveMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yf.wu
 * @Date 2019-06-23
 * @Description TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceiveMapperTest {

    @Autowired
    private ReceiveMapper receiveMapper;

    @Test
    public void selectMaxPrimary() {
        String primary = receiveMapper.selectMaxPrimary("CT_LINKCOMP", "ID");
        System.out.println("primary:" + primary);
    }

    @Test
    public void insertData() {
        Map<String, Object> params = new HashMap<>();
        params.put("ID", (int)(1+Math.random()*1000));
        params.put("lastname", "TOM");
        params.put("email", "123456@qq.com");
        params.put("gender", 20);

        long employee = 0;
        try {
            employee = receiveMapper.insertData("EMPLOYEE", params);
        } catch (Exception e) {
            System.out.println("插入失败："+e.getMessage());
            e.printStackTrace();
        }
    }
}