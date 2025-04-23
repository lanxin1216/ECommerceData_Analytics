package com.example.ecommercedata_analytics_backend.dao;

import java.util.List;
import java.util.Map;

/**
 * 商品购买行为分析
 */
public interface ProductPurchaseAnalysisDao {

    /**
     * 获取每月平均客单价（ARPU）
     */
    List<Map<String, Object>> getMonthlyARPUTrend();

    /**
     * 获取销量最多的前10个商品
     */
    List<Map<String, Object>> getTopSellingProducts();
}
