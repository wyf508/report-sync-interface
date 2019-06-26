package com.djhu.controller;

import com.djhu.config.TablesConfig;
import com.djhu.dao.provide.ProvideMapper;
import com.djhu.dao.receive.ReceiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private TablesConfig tablesConfig;


    @RequestMapping("/getSourceData")
    public Object getSource() {
        Object result = receiveMapper.selectMaxPrimary(tablesConfig);
        return result;
    }

    @RequestMapping("/getProvideData")
    public List getProvide() {
        List list = provideMapper.selectProvideData(tablesConfig);
        return list;
    }

}
