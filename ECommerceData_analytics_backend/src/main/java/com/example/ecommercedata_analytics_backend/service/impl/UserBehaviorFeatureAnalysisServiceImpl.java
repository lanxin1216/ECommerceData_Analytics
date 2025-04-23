package com.example.ecommercedata_analytics_backend.service.impl;

import com.example.ecommercedata_analytics_backend.common.BusinessException;
import com.example.ecommercedata_analytics_backend.constant.ErrorCode;
import com.example.ecommercedata_analytics_backend.dao.UserBehaviorFeatureAnalysisDao;
import com.example.ecommercedata_analytics_backend.service.UserBehaviorFeatureAnalysisService;
import com.example.ecommercedata_analytics_backend.util.RedisCacheUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserBehaviorFeatureAnalysisServiceImpl implements UserBehaviorFeatureAnalysisService {

    @Resource
    private UserBehaviorFeatureAnalysisDao analysisDao;
    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Override
    public List<Map<String, Object>> getGenderDistribution() {
        try {
            // 加载缓存
            String cacheKey = "gender_distribution";
            List<Map<String, Object>> cached = redisCacheUtil.getListMap(cacheKey);
            if (cached != null) {
                return cached;
            }

            List<Map<String, Object>> mapList = analysisDao.queryGenderDistribution();
            // 存入缓存
            redisCacheUtil.setCache(cacheKey, mapList);

            return mapList;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "获取性别分布失败");
        }
    }

    @Override
    public List<Map<String, Object>> getAgeStructure() {
        try {
            // 加载缓存
            String cacheKey = "age_structure";
            List<Map<String, Object>> cached = redisCacheUtil.getListMap(cacheKey);
            if (cached != null) {
                return cached;
            }

            List<Map<String, Object>> mapList = analysisDao.queryAgeDistribution();
            // 存入缓存
            redisCacheUtil.setCache(cacheKey, mapList);

            return mapList;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "获取年龄结构失败");
        }
    }

    @Override
    public List<Map<String, Object>> getMembershipRatio() {
        try {
            // 加载缓存
            String cacheKey = "membership_ratio";
            List<Map<String, Object>> cached = redisCacheUtil.getListMap(cacheKey);
            if (cached != null) {
                return cached;
            }

            List<Map<String, Object>> mapList = analysisDao.queryMembershipRatio();
            // 存入缓存
            redisCacheUtil.setCache(cacheKey, mapList);

            return mapList;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "获取会员比例失败");
        }
    }

    @Override
    public List<Map<String, Object>> getProvinceDistribution() {
        try {
            // 加载缓存
            String cacheKey = "province_distribution";
            List<Map<String, Object>> cached = redisCacheUtil.getListMap(cacheKey);
            if (cached != null) {
                return cached;
            }
            List<Map<String, Object>> mapList = analysisDao.queryProvinceDistribution();
            // 存入缓存
            redisCacheUtil.setCache(cacheKey, mapList);

            return mapList;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "获取地域分布失败");
        }
    }
}

