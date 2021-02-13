package com.promotion.product.controller;

import com.promotion.product.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private TestDao testDao;

    @RequestMapping("/test")
    public int test(){
        return testDao.select();
    }
}
