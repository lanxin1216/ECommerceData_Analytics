package com.example.ecommercedata_analytics_backend.dao.impl;

import com.example.ecommercedata_analytics_backend.dao.ProductPurchaseAnalysisDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class ProductPurchaseAnalysisDaoImpl implements ProductPurchaseAnalysisDao {

    @Resource
    private JdbcTemplate hiveJdbcTemplate;

    /**
     * 获取每月平均客单价（ARPU）
     */
    @Override
    public List<Map<String, Object>> getMonthlyARPUTrend() {
        String sql = "SELECT order_month, ROUND(SUM(total_amount) / COUNT(order_id), 2) AS arpu " +
                     "FROM ecommerce.orders GROUP BY order_month";
        return hiveJdbcTemplate.queryForList(sql);
    }

    /**
     * 获取销量最多的前10个商品
     */
    @Override
    public List<Map<String, Object>> getTopSellingProducts() {
        String sql = "SELECT product_name, SUM(quantity) AS total_quantity " +
                     "FROM ecommerce.orders GROUP BY product_name LIMIT 10";
        return hiveJdbcTemplate.queryForList(sql);
    }
}
