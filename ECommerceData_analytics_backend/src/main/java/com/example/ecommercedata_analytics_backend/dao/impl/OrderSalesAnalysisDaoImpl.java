package com.example.ecommercedata_analytics_backend.dao.impl;

import com.example.ecommercedata_analytics_backend.dao.OrderSalesAnalysisDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Dao 实现类
 */
@Service
public class OrderSalesAnalysisDaoImpl implements OrderSalesAnalysisDao {

    @Resource
    private JdbcTemplate hiveJdbcTemplate;

    @Override
    public List<Map<String, Object>> queryOrderTrend(String type) {
        String groupBy = "day".equalsIgnoreCase(type) ? "date_format(order_time, 'yyyy-MM-dd')" : "substr(order_time, 1, 7)";
        String sql = String.format(
                "SELECT %s AS period, COUNT(*) AS order_count FROM orders GROUP BY %s",
                groupBy, groupBy);
        return hiveJdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> queryHotCategoryRanking() {
        String sql = "SELECT category, SUM(total_amount) AS total FROM orders GROUP BY category ORDER BY total DESC LIMIT 10";
        return hiveJdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> queryRegionSales() {
        String sql = "SELECT delivery_province, SUM(total_amount) AS total FROM orders GROUP BY delivery_province";
        return hiveJdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> queryAgeGroupConsumption() {
        String sql = "SELECT " +
                     "CASE WHEN age BETWEEN 18 AND 25 THEN '18-25' " +
                     "     WHEN age BETWEEN 26 AND 35 THEN '26-35' " +
                     "     WHEN age BETWEEN 36 AND 45 THEN '36-45' " +
                     "     WHEN age BETWEEN 46 AND 60 THEN '46-60' " +
                     "     ELSE '60+' END AS age_group, " +
                     "SUM(total_amount) AS total " +
                     "FROM orders " +
                     "GROUP BY CASE WHEN age BETWEEN 18 AND 25 THEN '18-25' " +
                     "             WHEN age BETWEEN 26 AND 35 THEN '26-35' " +
                     "             WHEN age BETWEEN 36 AND 45 THEN '36-45' " +
                     "             WHEN age BETWEEN 46 AND 60 THEN '46-60' " +
                     "             ELSE '60+' END";
        return hiveJdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> queryMemberComparison() {
        String sql = "SELECT is_member, COUNT(*) AS order_count, SUM(total_amount) AS total FROM orders GROUP BY is_member";
        return hiveJdbcTemplate.queryForList(sql);
    }
}