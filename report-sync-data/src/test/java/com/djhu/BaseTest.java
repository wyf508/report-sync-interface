package com.djhu;

import org.junit.After;
import org.junit.Before;

/**
 * @author wy.wu
 * @date 2019-06-25-14:56
 */
public class BaseTest {

    @Before
    public void before() {
        System.out.println("----执行测试开始------");
    }

    @After
    public void after() {
        System.out.println("----执行测试接收------");
    }

}
