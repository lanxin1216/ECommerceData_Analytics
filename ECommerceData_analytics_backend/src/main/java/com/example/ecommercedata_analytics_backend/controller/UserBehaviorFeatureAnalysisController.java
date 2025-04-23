package com.example.ecommercedata_analytics_backend.controller;

import com.example.ecommercedata_analytics_backend.common.BaseResponse;
import com.example.ecommercedata_analytics_backend.common.ResultUtils;
import com.example.ecommercedata_analytics_backend.service.UserBehaviorFeatureAnalysisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户行为与特征分析
 */
@RestController
@RequestMapping("/api/analysis/user")
public class UserBehaviorFeatureAnalysisController {

    @Resource
    private UserBehaviorFeatureAnalysisService analysisService;

    /**
     * 用户性别分布分析
     */
    @GetMapping("/gender-distribution")
    public BaseResponse<List<Map<String, Object>>> getGenderDistribution() {
        List<Map<String, Object>> result = analysisService.getGenderDistribution();
        return ResultUtils.success(result);
    }

    /**
     * 用户年龄结构分析
     */
    @GetMapping("/age-structure")
    public BaseResponse<List<Map<String, Object>>> getAgeStructure() {
        List<Map<String, Object>> result = analysisService.getAgeStructure();
        return ResultUtils.success(result);
    }

    /**
     * 会员用户占比分析
     */
    @GetMapping("/membership-ratio")
    public BaseResponse<List<Map<String, Object>>> getMembershipRatio() {
        List<Map<String, Object>> result = analysisService.getMembershipRatio();
        return ResultUtils.success(result);
    }

    /**
     * 用户地域分布（省份）
     */
    @GetMapping("/province-distribution")
    public BaseResponse<List<Map<String, Object>>> getProvinceDistribution() {
        List<Map<String, Object>> result = analysisService.getProvinceDistribution();
        return ResultUtils.success(result);
    }
}

