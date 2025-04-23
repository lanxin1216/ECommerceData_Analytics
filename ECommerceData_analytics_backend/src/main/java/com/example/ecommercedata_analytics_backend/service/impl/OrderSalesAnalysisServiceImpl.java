package com.example.ecommercedata_analytics_backend.service.impl;

import com.example.ecommercedata_analytics_backend.common.BusinessException;
import com.example.ecommercedata_analytics_backend.constant.ErrorCode;
import com.example.ecommercedata_analytics_backend.dao.OrderSalesAnalysisDao;
import com.example.ecommercedata_analytics_backend.service.OrderSalesAnalysisService;
import com.example.ecommercedata_analytics_backend.util.RedisCacheUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Service 实现类
 */
@Service
public class OrderSalesAnalysisServiceImpl implements OrderSalesAnalysisService {

    @Resource
    private OrderSalesAnalysisDao orderSalesAnalysisDao;

    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Override
    public List<Map<String, Object>> getOrderTrend(String type) {
        if (type == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "类型为null");
        }
        // 加载缓存
        String cacheKey = "order_trend:" + type;
        List<Map<String, Object>> cached = redisCacheUtil.getListMap(cacheKey);
        if (cached != null) {
            return cached;
        }

        List<Map<String, Object>> queryList = orderSalesAnalysisDao.queryOrderTrend(type);

        List<String> xAxis = new ArrayList<>();
        List<Long> yAxis = new ArrayList<>();

        for (Map<String, Object> row : queryList) {
            xAxis.add(String.valueOf(row.get("period")));
            yAxis.add(((Number) row.get("order_count")).longValue());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("xAxis", xAxis);
        result.put("series", Collections.singletonList(
                new HashMap<String, Object>() {{
                    put("name", "订单量");
                    put("type", "line");
                    put("data", yAxis);
                }}
        ));
        List<Map<String, Object>> mapList = Collections.singletonList(result);

        // 存入缓存
        redisCacheUtil.setCache(cacheKey, mapList);
        return mapList;
    }

    @Override
    public List<Map<String, Object>> getHotCategoryRanking() {
        // 加载缓存
        String cacheKey = "hot_category_ranking";
        List<Map<String, Object>> cached = redisCacheUtil.getListMap(cacheKey);
        if (cached != null) {
            return cached;
        }

        List<Map<String, Object>> queryList = orderSalesAnalysisDao.queryHotCategoryRanking();

        List<String> xAxis = new ArrayList<>();
        List<Double> yAxis = new ArrayList<>();

        for (Map<String, Object> row : queryList) {
            xAxis.add(String.valueOf(row.get("category")));
            yAxis.add(((Number) row.get("total")).doubleValue());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("xAxis", xAxis);
        result.put("series", Collections.singletonList(
                new HashMap<String, Object>() {{
                    put("name", "销售额");
                    put("type", "bar");
                    put("data", yAxis);
                }}
        ));
        List<Map<String, Object>> mapList = Collections.singletonList(result);

        // 存入缓存
        redisCacheUtil.setCache(cacheKey, mapList);
        return mapList;
    }

    @Override
    public List<Map<String, Object>> getRegionSales() {
        // 加载缓存
        String cacheKey = "region_sales";
        List<Map<String, Object>> cached = redisCacheUtil.getListMap(cacheKey);
        if (cached != null) {
            return cached;
        }

        List<Map<String, Object>> queryList = orderSalesAnalysisDao.queryRegionSales();

        List<String> xAxis = new ArrayList<>();
        List<Double> yAxis = new ArrayList<>();

        for (Map<String, Object> row : queryList) {
            xAxis.add(String.valueOf(row.get("delivery_province")));
            yAxis.add(((Number) row.get("total")).doubleValue());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("xAxis", xAxis);
        result.put("series", Collections.singletonList(
                new HashMap<String, Object>() {{
                    put("name", "销售额");
                    put("type", "bar");
                    put("data", yAxis);
                }}
        ));

        List<Map<String, Object>> mapList = Collections.singletonList(result);

        // 存入缓存
        redisCacheUtil.setCache(cacheKey, mapList);
        return mapList;
    }

    @Override
    public List<Map<String, Object>> getAgeGroupConsumption() {
        // 加载缓存
        String cacheKey = "age_group_consumption";
        List<Map<String, Object>> cached = redisCacheUtil.getListMap(cacheKey);
        if (cached != null) {
            return cached;
        }

        List<Map<String, Object>> queryList = orderSalesAnalysisDao.queryAgeGroupConsumption();

        List<String> xAxis = new ArrayList<>();
        List<Double> yAxis = new ArrayList<>();

        for (Map<String, Object> row : queryList) {
            xAxis.add(String.valueOf(row.get("age_group")));
            yAxis.add(((Number) row.get("total")).doubleValue());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("xAxis", xAxis);
        result.put("series", Collections.singletonList(
                new HashMap<String, Object>() {{
                    put("name", "消费金额");
                    put("type", "bar");
                    put("data", yAxis);
                }}
        ));
        List<Map<String, Object>> mapList = Collections.singletonList(result);

        // 存入缓存
        redisCacheUtil.setCache(cacheKey, mapList);
        return mapList;
    }

    @Override
    public List<Map<String, Object>> getMemberComparison() {
        // 加载缓存
        String cacheKey = "member_comparison";
        List<Map<String, Object>> cached = redisCacheUtil.getListMap(cacheKey);
        if (cached != null) {
            return cached;
        }

        List<Map<String, Object>> queryList = orderSalesAnalysisDao.queryMemberComparison();

        List<String> xAxis = new ArrayList<>();
        List<Long> orderCountList = new ArrayList<>();
        List<Double> amountList = new ArrayList<>();

        for (Map<String, Object> row : queryList) {
            String isMember = "是".equals(String.valueOf(row.get("is_member"))) ? "会员" : "非会员";
            xAxis.add(isMember);
            orderCountList.add(((Number) row.get("order_count")).longValue());
            amountList.add(((Number) row.get("total")).doubleValue());
        }

        Map<String, Object> orderSeries = new HashMap<>();
        orderSeries.put("name", "订单数");
        orderSeries.put("type", "bar");
        orderSeries.put("data", orderCountList);

        Map<String, Object> amountSeries = new HashMap<>();
        amountSeries.put("name", "消费总额");
        amountSeries.put("type", "bar");
        amountSeries.put("data", amountList);

        Map<String, Object> result = new HashMap<>();
        result.put("xAxis", xAxis);
        result.put("series", Arrays.asList(orderSeries, amountSeries));

        List<Map<String, Object>> mapList = Collections.singletonList(result);

        // 存入缓存
        redisCacheUtil.setCache(cacheKey, mapList);
        return mapList;
    }
}