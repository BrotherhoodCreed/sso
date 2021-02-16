package com.promotion.product.controller;

import com.soso.product1.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RedisController {



    @Autowired
    private TestDao testDao;

    @RequestMapping("/test")
    public String test(){
        return "index1";
    }


    @RequestMapping("/index/list")
    @ResponseBody
    public String list(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1","2");
        return jsonObject.toJSONString();
    }
}
