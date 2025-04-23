package com.example.ecommercedata_analytics_backend.service;

import java.util.Map;

/**
 * 商品购买行为分析
 */
public interface ProductPurchaseAnalysisService {

    /**
     * 平均客单价分析（ARPU）
     */
    Map<String, Object> getMonthlyARPUTrend();

    /**
     * 热销商品分析-单品销量排行
     */
    Map<String, Object> getTopSellingProducts();
}
