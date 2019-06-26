package com.djhu.dao;

import com.djhu.BaseTest;
import com.djhu.config.TablesConfig;
import com.djhu.dao.provide.ProvideMapper;
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
public class ProvideMapperTest extends BaseTest {

    @Autowired
    private ProvideMapper provideMapper;
    @Autowired
    private TablesConfig tablesConfig;

    @Test
    public void selectTargetData() {
//        List list = provideMapper.selectProvideData(tablesConfig);
        System.out.println("查询数据：");

    }
}