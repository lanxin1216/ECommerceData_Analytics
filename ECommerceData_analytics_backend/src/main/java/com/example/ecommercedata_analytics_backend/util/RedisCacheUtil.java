package com.example.ecommercedata_analytics_backend.util;

import com.example.ecommercedata_analytics_backend.common.BusinessException;
import com.example.ecommercedata_analytics_backend.constant.ErrorCode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
public class RedisCacheUtil {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisCacheUtil(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = new ObjectMapper();
    }

    private Duration getRandomTTL() {
        int seconds = ThreadLocalRandom.current().nextInt(30 * 60, 40 * 60); // 1800 ~ 2400 秒
        return Duration.ofSeconds(seconds);
    }

    public void setCache(String key, Object value) {
        try {
            String json = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, json, getRandomTTL());
        } catch (Exception e) {
            log.error("Failed to set cache:", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "redis缓存异常");
        }
    }

    public List<Map<String, Object>> getListMap(String key) {
        try {
            String json = redisTemplate.opsForValue().get(key);
            if (json == null) return null;
            return objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            log.error("Failed to set cache:", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "redis缓存异常");
        }
    }

    public Map<String, Object> getMap(String key) {
        try {
            String json = redisTemplate.opsForValue().get(key);
            if (json == null) return null;
            return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            log.error("Failed to set cache:", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "redis缓存异常");
        }
    }
}
