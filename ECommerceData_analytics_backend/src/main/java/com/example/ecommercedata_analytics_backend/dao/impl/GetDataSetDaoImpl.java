package com.example.ecommercedata_analytics_backend.dao.impl;

import com.example.ecommercedata_analytics_backend.dao.GetDataSetDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class GetDataSetDaoImpl implements GetDataSetDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getUsers(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        String sql = String.format("SELECT * FROM users LIMIT %d OFFSET %d", pageSize, offset);
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> getOrderSample() {
        String sql = "SELECT * FROM orders LIMIT 1000";
        return jdbcTemplate.queryForList(sql);
    }

}
