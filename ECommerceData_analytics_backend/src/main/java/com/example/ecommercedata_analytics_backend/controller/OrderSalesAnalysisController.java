package com.example.ecommercedata_analytics_backend.controller;

import com.example.ecommercedata_analytics_backend.common.BaseResponse;
import com.example.ecommercedata_analytics_backend.common.ResultUtils;
import com.example.ecommercedata_analytics_backend.service.OrderSalesAnalysisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 订单与销售分析
 */
@RestController
@RequestMapping("/api/analysis/sales")
public class OrderSalesAnalysisController {

    @Resource
    private OrderSalesAnalysisService orderSalesAnalysisService;

    /**
     * 每日/每月订单量趋势
     */
    @GetMapping("/order-trend")
    public BaseResponse<List<Map<String, Object>>> getOrderTrend(@RequestParam("type") String type) {
        return ResultUtils.success(orderSalesAnalysisService.getOrderTrend(type));
    }

    /**
     * 热销商品类别排行
     */
    @GetMapping("/hot-category")
    public BaseResponse<List<Map<String, Object>>> getHotCategoryRanking() {
        return ResultUtils.success(orderSalesAnalysisService.getHotCategoryRanking());
    }

    /**
     * 各省/城市销售额排行
     */
    @GetMapping("/region-sales")
    public BaseResponse<List<Map<String, Object>>> getRegionSales() {
        return ResultUtils.success(orderSalesAnalysisService.getRegionSales());
    }

    /**
     * 用户年龄段消费金额分析
     */
    @GetMapping("/age-consumption")
    public BaseResponse<List<Map<String, Object>>> getAgeGroupConsumption() {
        return ResultUtils.success(orderSalesAnalysisService.getAgeGroupConsumption());
    }

    /**
     * 会员与非会员对比分析
     */
    @GetMapping("/member-compare")
    public BaseResponse<List<Map<String, Object>>> getMemberComparison() {
        return ResultUtils.success(orderSalesAnalysisService.getMemberComparison());
    }
}