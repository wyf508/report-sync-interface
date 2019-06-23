package com.djhu.controller;

import com.djhu.dao.receive.ReceiveMapper;
import com.djhu.dao.provide.ProvideMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author yf.wu
 * @Date 2019-06-23
 * @Description TODO
 */
@RestController
public class TestDataController {

    @Autowired
    private ReceiveMapper receiveMapper;

    @Autowired
    private ProvideMapper provideMapper;


    @RequestMapping("/getSourceData")
    public String getSource(){
        String result = receiveMapper.selectMaxPrimary("CT_LINKCOMP", "ID");
        return result;
    }

    @RequestMapping("/getProvideData")
    public List getProvide(){
        Map<String, Object> map = new HashMap<>();
        map.put("tableName","CT_LINKCOMP");
        map.put("sourceKey","id");
        map.put("provideKeyVal",100);
        map.put("condition","CELLTREEID > 14200 ");
        map.put("pageSize",1);
        List list = provideMapper.selectProvideData(map);
        return list;
    }

}
