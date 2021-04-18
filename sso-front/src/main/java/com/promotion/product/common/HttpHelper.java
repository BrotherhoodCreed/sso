package com.promotion.product.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

/**
 * 提供通过HTTP协议获取内容的方法 <br/>
 * 所有提供方法中的params参数在内部不会进行自动的url encode，如果提交参数需要进行url encode，请调用方自行处理
 * @Description: HTTP请求代理工具
 * @author lushuifa
 * @date 2016年11月18日 上午11:21:05
 */
public class HttpHelper {
    /**
     * 普通get请求
     * @param url
     * @return
     */
    public static JSONObject httpGet(String url) {
        String result = "";
        HttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(result);
    }

    /**
     * 普通post请求：application/json和application/x-www-form-urlencoded
     * @param url
     * @param params
     * @param contentType
     * @return
     */
    public static String post(String url, String params, String contentType) {
        String result = "";
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-type", contentType + "; charset=utf-8");
        HttpResponse response = null;
        try {
            StringEntity entity = new StringEntity(params, Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
            // 后期记录异常
        }

        try {
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            // 后期记录异常
        }

        return result;
    }

}