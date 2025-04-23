package com.example.ecommercedata_analytics_backend.service;

import java.util.List;
import java.util.Map;

/**
 * 订单与销售分析
 */
public interface OrderSalesAnalysisService {
    /**
     * 每日/每月订单量趋势
     */
    List<Map<String, Object>> getOrderTrend(String type);

    /**
     * 热销商品类别排行
     */
    List<Map<String, Object>> getHotCategoryRanking();

    /**
     * 各省/城市销售额排行
     */
    List<Map<String, Object>> getRegionSales();

    /**
     * 用户年龄段消费金额分析
     */
    List<Map<String, Object>> getAgeGroupConsumption();

    /**
     * 会员与非会员对比分析
     */
    List<Map<String, Object>> getMemberComparison();
}
