package com.example.ecommercedata_analytics_backend.dao.impl;

import com.example.ecommercedata_analytics_backend.dao.HiveDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * hive 操作服务
 */
@Service
public class HiveDaoImpl implements HiveDao {

    @Resource
    private JdbcTemplate hiveJdbcTemplate;

    @Override
    public void testHiveConnection() {
        String sql = "SHOW TABLES";
        hiveJdbcTemplate.queryForList(sql).forEach(System.out::println);
    }

    /**
     * 开启 Hive 动态分区设置
     */
    @Override
    public void setDynamicPartitionConfig() {
        hiveJdbcTemplate.execute("SET hive.exec.dynamic.partition = true");
        hiveJdbcTemplate.execute("SET hive.exec.dynamic.partition.mode = nonstrict");
    }

    @Override
    public void loadDataIntoUsers(String hdfsPath) {
        String sql = String.format(
                "LOAD DATA INPATH '%s' INTO TABLE ecommerce.users", hdfsPath
        );
        hiveJdbcTemplate.execute(sql);
    }

    /**
     * 加载 CSV 文件到临时表中
     */
    @Override
    public void loadDataIntoTempOrders(String hdfsPath) {
        String sql = String.format("LOAD DATA INPATH '%s' OVERWRITE INTO TABLE temp_orders", hdfsPath);
        hiveJdbcTemplate.execute(sql);
    }

    /**
     * 将临时表数据插入分区表 ecommerce.orders
     */
    @Override
    public void insertTempIntoOrders() {
        String sql =
                "INSERT INTO TABLE ecommerce.orders PARTITION(order_month) " +
                "SELECT order_id, user_id, order_time, category, sub_category, product_name, " +
                "unit_price, quantity, total_amount, delivery_province, delivery_city, " +
                "gender, age, is_member, order_month " +
                "FROM ecommerce.temp_orders";
        hiveJdbcTemplate.execute(sql);
        hiveJdbcTemplate.execute("MSCK REPAIR TABLE ecommerce.orders");
    }
}