package com.example.ecommercedata_analytics_backend.service.impl;

import com.example.ecommercedata_analytics_backend.common.BusinessException;
import com.example.ecommercedata_analytics_backend.constant.ErrorCode;
import com.example.ecommercedata_analytics_backend.dao.UserBehaviorFeatureAnalysisDao;
import com.example.ecommercedata_analytics_backend.service.UserBehaviorFeatureAnalysisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserBehaviorFeatureAnalysisServiceImpl implements UserBehaviorFeatureAnalysisService {

    @Resource
    private UserBehaviorFeatureAnalysisDao analysisDao;

    @Override
    public List<Map<String, Object>> getGenderDistribution() {
        try {
            return analysisDao.queryGenderDistribution();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "获取性别分布失败");
        }
    }

    @Override
    public List<Map<String, Object>> getAgeStructure() {
        try {
            return analysisDao.queryAgeDistribution();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "获取年龄结构失败");
        }
    }

    @Override
    public List<Map<String, Object>> getMembershipRatio() {
        try {
            return analysisDao.queryMembershipRatio();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "获取会员比例失败");
        }
    }

    @Override
    public List<Map<String, Object>> getProvinceDistribution() {
        try {
            return analysisDao.queryProvinceDistribution();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "获取地域分布失败");
        }
    }
}

