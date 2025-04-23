-- 创建数据库
CREATE DATABASE IF NOT EXISTS ecommerce;

-- 使用数据库
USE ecommerce;

-- 创建用户表
CREATE EXTERNAL TABLE IF NOT EXISTS users
(
    user_id   STRING COMMENT '用户ID',
    gender    STRING COMMENT '性别',
    age       INT COMMENT '年龄',
    is_member STRING COMMENT '是否会员',
    province  STRING COMMENT '常用省份',
    city      STRING COMMENT '常用城市'
)
    ROW FORMAT DELIMITED
        FIELDS TERMINATED BY ','
    STORED AS TEXTFILE
    LOCATION '/ecommerce/adidas/users'
    TBLPROPERTIES ("skip.header.line.count" = "1");

-- 创建订单表
CREATE EXTERNAL TABLE IF NOT EXISTS orders
(
    order_id          STRING COMMENT '订单ID',
    user_id           STRING COMMENT '用户ID',
    order_time        STRING COMMENT '下单时间',
    category          STRING COMMENT '商品大类',
    sub_category      STRING COMMENT '商品子类',
    product_name      STRING COMMENT '商品名称',
    unit_price        DOUBLE COMMENT '单价',
    quantity          INT COMMENT '购买数量',
    total_amount      DOUBLE COMMENT '订单总额',
    delivery_province STRING COMMENT '收货省份',
    delivery_city     STRING COMMENT '收货城市',
    gender            STRING COMMENT '性别',
    age               INT COMMENT '年龄',
    is_member         STRING COMMENT '是否会员'
)
    PARTITIONED BY (order_month STRING COMMENT '订单月份分区')
    ROW FORMAT DELIMITED
        FIELDS TERMINATED BY ','
    STORED AS ORC -- 高性能列式存储
    LOCATION '/ecommerce/adidas/orders'
    TBLPROPERTIES ("skip.header.line.count" = "1");

-- 创建临时表 temp_orders
CREATE TABLE IF NOT EXISTS temp_orders
(
    order_id          STRING,
    user_id           STRING,
    order_time        STRING,
    category          STRING,
    sub_category      STRING,
    product_name      STRING,
    unit_price        DOUBLE,
    quantity          INT,
    total_amount      DOUBLE,
    delivery_province STRING,
    delivery_city     STRING,
    gender            STRING,
    age               INT,
    is_member         STRING,
    order_month       STRING
)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
    STORED AS TEXTFILE
    LOCATION '/ecommerce/adidas/temp_orders'
    TBLPROPERTIES ('skip.header.line.count' = '1');


SET hive.exec.dynamic.partition = true;
SET hive.exec.dynamic.partition.mode = nonstrict;

-- 将临时表数据插入分区表 orders
INSERT INTO TABLE ecommerce.orders PARTITION (order_month)
SELECT order_id,
       user_id,
       order_time,
       category,
       sub_category,
       product_name,
       unit_price,
       quantity,
       total_amount,
       delivery_province,
       delivery_city,
       gender,
       age,
       is_member,
       order_month
FROM ecommerce.temp_orders;

-- 修复分区表 orders
MSCK REPAIR TABLE ecommerce.orders;

-- OrderSalesAnalysisDaoImpl
SELECT category, SUM(total_amount) AS sales
FROM orders
GROUP BY category
LIMIT 10;