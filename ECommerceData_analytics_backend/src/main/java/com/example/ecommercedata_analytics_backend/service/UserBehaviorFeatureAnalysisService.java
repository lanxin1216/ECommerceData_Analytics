package com.example.ecommercedata_analytics_backend.service;

import java.util.List;
import java.util.Map;

/**
 * 用户行为与特征分析
 */
public interface UserBehaviorFeatureAnalysisService {

    /**
     * 用户性别分布
     */
    List<Map<String, Object>> getGenderDistribution();

    /**
     * 年龄结构分析
     */
    List<Map<String, Object>> getAgeStructure();

    /**
     * 会员用户占比
     */
    List<Map<String, Object>> getMembershipRatio();

    /**
     * 用户地域分布（按省份）
     */
    List<Map<String, Object>> getProvinceDistribution();
}

