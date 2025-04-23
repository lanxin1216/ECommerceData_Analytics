package com.example.ecommercedata_analytics_backend.controller;

import com.example.ecommercedata_analytics_backend.common.BaseResponse;
import com.example.ecommercedata_analytics_backend.common.BusinessException;
import com.example.ecommercedata_analytics_backend.common.ResultUtils;
import com.example.ecommercedata_analytics_backend.constant.ErrorCode;
import com.example.ecommercedata_analytics_backend.service.ProductPurchaseAnalysisService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 商品购买行为分析
 */
@RestController
@RequestMapping("/analysis/purchase")
public class ProductPurchaseAnalysisController {

    @Resource
    private ProductPurchaseAnalysisService productPurchaseAnalysisService;

    /**
     * 平均客单价分析（ARPU）
     */
    @GetMapping("/arpu")
    public BaseResponse<Map<String, Object>> getARPUTrend() {
        try {
            return ResultUtils.success(productPurchaseAnalysisService.getMonthlyARPUTrend());
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "获取平均客单价失败");
        }
    }

    /**
     * 热销商品分析-单品销量排行
     */
    @GetMapping("/top-products")
    public BaseResponse<Map<String, Object>> getTopSellingProducts() {
        try {
            return ResultUtils.success(productPurchaseAnalysisService.getTopSellingProducts());
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "获取热销商品失败");
        }
    }
}
