package com.example.ecommercedata_analytics_backend.service.impl;

import com.example.ecommercedata_analytics_backend.dao.GetDataSetDao;
import com.example.ecommercedata_analytics_backend.service.GetDataSetService;
import com.example.ecommercedata_analytics_backend.util.RedisCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GetDataSetServiceImpl implements GetDataSetService {

    @Resource
    private GetDataSetDao getDataSetDao;

    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Override
    public List<Map<String, Object>> getUsers(int page, int pageSize) {
        // 加载缓存
        String cacheKey = "users_page:" + page + ":" + pageSize;
        List<Map<String, Object>> cached = redisCacheUtil.getListMap(cacheKey);
        if (cached != null) {
            return cached;
        }

        List<Map<String, Object>> users = getDataSetDao.getUsers(page, pageSize);
        // 存入缓存
        redisCacheUtil.setCache(cacheKey, users);
        return users;
    }

    @Override
    public List<Map<String, Object>> getOrderSample() {
        // 加载缓存
        String cacheKey = "order_sample";
        List<Map<String, Object>> cached = redisCacheUtil.getListMap(cacheKey);
        if (cached != null) {
            return cached;
        }

        List<Map<String, Object>> orderSample = getDataSetDao.getOrderSample();

        // 存入缓存
        redisCacheUtil.setCache(cacheKey, orderSample);
        return orderSample;
    }

}
