package com.soso.product1.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import  redis.clients.jedis.JedisPool;


@Configuration
public class RedisCache{

    private  static  final Logger lg = LoggerFactory.getLogger(RedisCache.class);

    // 默认缓存过期5分钟 单位是秒
    private int DEFAULT_EXPIRE = 5*60;

    @Autowired
    private static JedisPool POOL ;

    private Jedis getResource() {
        try {
            Jedis j = POOL.getResource();
            return j;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean putStr(String key, String value, int expire) {
        Jedis jedis = getResource();
        try {
            if (jedis == null)
                return false;
            jedis.setex((key).getBytes(), expire, value.getBytes());
            // 不抛出异常认为是成功的
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            colse(jedis);
        }
    }

    private void colse(Jedis jedis) {
        if (jedis != null)
            POOL.returnResource(jedis);
    }

    public boolean remove(String key) {
        Jedis jedis = getResource();

        try {
            if (jedis == null)
                return false;

            jedis.del(key);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            colse(jedis);
        }
    }


    public void clear() {
    }

    public <T> T get(String key, Class<? extends T> clazz) {
        Jedis jedis = getResource();

        try {
            if (jedis == null)
                return null;
            String str = new String(jedis.get((key).getBytes()));
            return JSON.parseObject(str, clazz);
        } catch (Exception e) {
            return null;
        } finally {
            colse(jedis);
        }
    }


    public boolean put(String key, Object value) {
        return put(key, value, DEFAULT_EXPIRE);
    }


    public boolean put(String key, Object value, int expire) {
        return putStr(key, JSON.toJSONString(value), expire);
    }

    public int getDEFAULT_EXPIRE() {
        return DEFAULT_EXPIRE;
    }

    public void setDEFAULT_EXPIRE(int dEFAULT_EXPIRE) {
        DEFAULT_EXPIRE = dEFAULT_EXPIRE;
    }

    @Bean
    public CacheErrorHandler errorHandler(){
        lg.info("初始化 -> [{}]", "Redis CacheErrorHandler");
        //异常处理，当Redis发生异常时，打印日志，但是程序正常走
        CacheErrorHandler cacheErrorHandler =new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                lg.error("Redis occur handleCacheGetError：key -> [{}]", key, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                lg.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", key, value, e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                lg.error("Redis occur handleCacheEvictError：key -> [{}]", key, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                lg.error("Redis occur handleCacheClearError：", e);
            }
        };
        return cacheErrorHandler;
    }



}
