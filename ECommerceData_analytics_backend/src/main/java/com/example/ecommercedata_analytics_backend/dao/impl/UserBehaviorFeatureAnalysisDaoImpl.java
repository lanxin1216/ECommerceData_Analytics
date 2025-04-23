package com.example.ecommercedata_analytics_backend.dao.impl;

import com.example.ecommercedata_analytics_backend.dao.UserBehaviorFeatureAnalysisDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class UserBehaviorFeatureAnalysisDaoImpl implements UserBehaviorFeatureAnalysisDao {

    @Resource
    private JdbcTemplate hiveJdbcTemplate;

    @Override
    public List<Map<String, Object>> queryGenderDistribution() {
        String sql = "SELECT gender AS name, COUNT(*) AS value FROM ecommerce.users GROUP BY gender";
        return hiveJdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> queryAgeDistribution() {
        String sql =
                "SELECT " +
                "CASE " +
                "  WHEN age BETWEEN 0 AND 17 THEN '0-17' " +
                "  WHEN age BETWEEN 18 AND 25 THEN '18-25' " +
                "  WHEN age BETWEEN 26 AND 35 THEN '26-35' " +
                "  WHEN age BETWEEN 36 AND 45 THEN '36-45' " +
                "  WHEN age BETWEEN 46 AND 60 THEN '46-60' " +
                "  ELSE '60+' " +
                "END AS name, COUNT(*) AS value " +
                "FROM ecommerce.users GROUP BY " +
                "CASE " +
                "  WHEN age BETWEEN 0 AND 17 THEN '0-17' " +
                "  WHEN age BETWEEN 18 AND 25 THEN '18-25' " +
                "  WHEN age BETWEEN 26 AND 35 THEN '26-35' " +
                "  WHEN age BETWEEN 36 AND 45 THEN '36-45' " +
                "  WHEN age BETWEEN 46 AND 60 THEN '46-60' " +
                "  ELSE '60+' " +
                "END";
        return hiveJdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> queryMembershipRatio() {
        String sql = "SELECT " +
                     "CASE WHEN is_member = '是' THEN '会员' ELSE '非会员' END AS name, " +
                     "COUNT(*) AS value FROM ecommerce.users GROUP BY is_member";
        return hiveJdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> queryProvinceDistribution() {
        String sql = "SELECT delivery_province AS name, COUNT(*) AS value " +
                     "FROM ecommerce.orders GROUP BY delivery_province";
        return hiveJdbcTemplate.queryForList(sql);
    }
}
