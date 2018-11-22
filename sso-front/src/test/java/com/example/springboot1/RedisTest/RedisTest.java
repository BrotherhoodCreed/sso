package com.example.springboot1.RedisTest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/redisTest")
    public  String redisTest(){

        stringRedisTemplate.opsForValue().set("k5","test");

        return stringRedisTemplate.opsForValue().get("k5");
    }

}
