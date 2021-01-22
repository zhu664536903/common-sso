package com.gczhu.commonsso.config;

import com.gczhu.commonsso.common.utils.RedisUtil;
import com.gczhu.commonsso.common.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


@Configuration
public class UtilsConfig {

    /**
     * 注入封装RedisTemplate
    * @Title: redisUtil 
    * @return RedisUtil
    * @autor zgc
    * @date 2017年12月21日
    * @throws
     */
    @Bean(name = "redisUtil")
    public RedisUtil redisUtil(@Autowired StringRedisTemplate redisTemplate) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }
    @Bean(name = "tokenUtil")
    public TokenUtil tokenUtil(){
        return new TokenUtil();
    }
} 
