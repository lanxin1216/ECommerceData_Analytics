package com.example.ecommercedata_analytics_backend.service.impl;

import com.example.ecommercedata_analytics_backend.dao.ProductPurchaseAnalysisDao;
import com.example.ecommercedata_analytics_backend.service.ProductPurchaseAnalysisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ProductPurchaseAnalysisServiceImpl implements ProductPurchaseAnalysisService {

    @Resource
    private ProductPurchaseAnalysisDao productPurchaseAnalysisDao;

    @Override
    public Map<String, Object> getMonthlyARPUTrend() {
        List<Map<String, Object>> list = productPurchaseAnalysisDao.getMonthlyARPUTrend();

        List<String> xAxis = new ArrayList<>();
        List<Double> data = new ArrayList<>();

        for (Map<String, Object> row : list) {
            xAxis.add((String) row.get("order_month"));
            data.add((Double) row.get("arpu"));
        }

        Map<String, Object> seriesItem = new HashMap<>();
        seriesItem.put("name", "ARPU");
        seriesItem.put("type", "line");
        seriesItem.put("data", data);

        List<Map<String, Object>> series = new ArrayList<>();
        series.add(seriesItem);

        Map<String, Object> result = new HashMap<>();
        result.put("xAxis", xAxis);
        result.put("series", series);
        return result;
    }

    @Override
    public Map<String, Object> getTopSellingProducts() {
        List<Map<String, Object>> list = productPurchaseAnalysisDao.getTopSellingProducts();

        List<String> productNames = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();

        for (Map<String, Object> row : list) {
            productNames.add((String) row.get("product_name"));
            quantities.add(((Long) row.get("total_quantity")).intValue());
        }

        Map<String, Object> seriesItem = new HashMap<>();
        seriesItem.put("name", "销量");
        seriesItem.put("type", "bar");
        seriesItem.put("data", quantities);

        List<Map<String, Object>> series = new ArrayList<>();
        series.add(seriesItem);

        Map<String, Object> result = new HashMap<>();
        result.put("xAxis", productNames);
        result.put("series", series);
        return result;
    }
}
