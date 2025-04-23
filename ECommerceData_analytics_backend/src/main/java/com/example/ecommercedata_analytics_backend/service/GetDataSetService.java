package com.example.ecommercedata_analytics_backend.service;


import java.util.List;
import java.util.Map;

/**
 * 获取数据集
 */
public interface GetDataSetService {

    /**
     * 分页获取用户数据
     */
    List<Map<String, Object>> getUsers(int page, int pageSize);

    /**
     * 获取1000条订单数据
     */
    List<Map<String, Object>> getOrderSample();


}
