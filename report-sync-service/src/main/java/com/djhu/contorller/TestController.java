package com.djhu.contorller;

import com.djhu.service.ReportSyncService;
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
public class TestController {

    @Autowired
    private ReportSyncService reportSyncService;

    @RequestMapping("/getTarget")
    public void getDate() throws Exception {
        reportSyncService.getProvideData();
    }

}
