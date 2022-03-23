package com.zero.springboot.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zero.hintmgr.HintMgrApplication;
import com.zero.hintmgr.dao.DummyDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HintMgrApplication.class) 
public class DummyServiceBTest {
    Logger log = LoggerFactory.getLogger(DummyServiceBTest.class);
    
    @Autowired
    private DummyDao dummyDao;
    
    @Test
    public void testFindSingleDummy() {
        List<String> list = new ArrayList<>();
        log.info("Dummy List: " + list);
        Assert.assertTrue(list.size() > 0);
    }
    
    @Test
    public void testFindMultipleDummy() {
        List<String> list = new ArrayList<>();
        log.info("Dummy List: " + list);
        Assert.assertTrue(list.size() == 0);
    }
}
