package com.imin.adminweb;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AdminWebApplication.class)
//@WebAppConfiguration
@Slf4j
public class BaseTest {

//    @Before
    public void before() {
        log.info("-----------------AdminWebApplication test start-----------------");
    }

//    @After
    public void after() {
        log.info("-----------------AdminWebApplication test end-----------------");
    }

//    @Test
    public void baseTest() {

    }
}
