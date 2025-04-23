package com.example.ecommercedata_analytics_backend.dao;


import java.util.List;
import java.util.Map;

/**
 * 获取数据集
 */
public interface GetDataSetDao {
    List<Map<String, Object>> getUsers(int page, int pageSize);

    List<Map<String, Object>> getOrderSample();


}
