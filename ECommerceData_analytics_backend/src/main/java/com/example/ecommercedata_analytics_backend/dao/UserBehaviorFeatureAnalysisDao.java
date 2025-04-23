package com.example.ecommercedata_analytics_backend.dao;

import java.util.List;
import java.util.Map;

/**
 * 用户行为与特征分析
 */
public interface UserBehaviorFeatureAnalysisDao {

    /**
     * 查询用户性别分布（返回字段 name, value）
     */
    List<Map<String, Object>> queryGenderDistribution();

    /**
     * 查询用户年龄结构（分组字段 name 为年龄段，value 为人数）
     */
    List<Map<String, Object>> queryAgeDistribution();

    /**
     * 查询会员与非会员比例（返回字段 name, value）
     */
    List<Map<String, Object>> queryMembershipRatio();

    /**
     * 查询用户地域分布（省份）（返回字段 name 为省份, value 为人数）
     */
    List<Map<String, Object>> queryProvinceDistribution();
}

