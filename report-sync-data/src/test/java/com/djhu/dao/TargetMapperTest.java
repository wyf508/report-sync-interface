package com.djhu.dao;

import com.djhu.dao.provide.ProvideMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author yf.wu
 * @Date 2019-06-23
 * @Description TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TargetMapperTest {

    @Autowired
    private ProvideMapper provideMapper;

    @Test
    public void selectTargetData() {
        Map<String, Object> map = new HashMap<>();
        map.put("provideTableName","CT_LINKCOMP");
        map.put("provideKey","id");
        map.put("receiveKeyVal",100);
        map.put("condition","CELLTREEID > 14200 ");
        map.put("pageSize",1);
        List list = provideMapper.selectProvideData(map);
        System.out.println("查询数据：" + list);

    }
}