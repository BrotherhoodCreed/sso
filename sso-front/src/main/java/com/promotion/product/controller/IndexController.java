package com.promotion.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.promotion.product.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {



    @Autowired
    private TestDao testDao;

    @RequestMapping("/index")
    public String test(){
        return "index";
    }


    @RequestMapping("/index/list")
    @ResponseBody
    public String list(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1","2");
        return jsonObject.toJSONString();
    }

    @RequestMapping("/detail")
    public String detail(){
        return "detail";
    }

}
