package com.example.ecommercedata_analytics_backend.dao;

import java.util.List;
import java.util.Map;

/**
 * 订单与销售分析
 */
public interface OrderSalesAnalysisDao {
    /**
     * 每日/每月订单量趋势
     */
    List<Map<String, Object>> queryOrderTrend(String type);

    /**
     * 热销商品类别排行
     */
    List<Map<String, Object>> queryHotCategoryRanking();

    /**
     * 各省/城市销售额排行
     */
    List<Map<String, Object>> queryRegionSales();

    /**
     * 用户年龄段消费金额分析
     */
    List<Map<String, Object>> queryAgeGroupConsumption();

    /**
     * 会员与非会员对比分析
     */
    List<Map<String, Object>> queryMemberComparison();
}