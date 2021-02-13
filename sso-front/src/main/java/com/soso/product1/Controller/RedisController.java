package com.soso.product1.Controller;

import com.soso.product1.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
