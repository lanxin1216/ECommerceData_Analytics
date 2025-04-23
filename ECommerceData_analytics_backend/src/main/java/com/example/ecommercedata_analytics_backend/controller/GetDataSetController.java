package com.example.ecommercedata_analytics_backend.controller;

import com.example.ecommercedata_analytics_backend.common.BaseResponse;
import com.example.ecommercedata_analytics_backend.common.ResultUtils;
import com.example.ecommercedata_analytics_backend.service.GetDataSetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 获取数据集
 */
@RestController
@RequestMapping("/api/dataset")
public class GetDataSetController {

    @Resource
    private GetDataSetService getDataSetService;

    /**
     * 获取用户数据集
     */
    @GetMapping("/users")
    public BaseResponse<List<Map<String, Object>>> getUsers(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize) {
        return ResultUtils.success(getDataSetService.getUsers(page, pageSize));
    }

    /**
     * 获取最多1000条订单数据
     */
    @GetMapping("/orders")
    public BaseResponse<List<Map<String, Object>>> getOrderSample() {
        return ResultUtils.success(getDataSetService.getOrderSample());
    }


}