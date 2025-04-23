package com.example.ecommercedata_analytics_backend.dao;

/**
 * hive 基础操作服务
 */
public interface HiveDao {

    // 测试连接
    void testHiveConnection();

    void setDynamicPartitionConfig();

    void loadDataIntoUsers(String hdfsPath);

    void loadDataIntoTempOrders(String hdfsPath);

    void insertTempIntoOrders();
}

