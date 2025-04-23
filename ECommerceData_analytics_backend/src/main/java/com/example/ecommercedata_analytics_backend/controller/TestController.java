package com.example.ecommercedata_analytics_backend.controller;

import com.example.ecommercedata_analytics_backend.common.BaseResponse;
import com.example.ecommercedata_analytics_backend.common.ResultUtils;
import com.example.ecommercedata_analytics_backend.dao.HiveDao;
import com.example.ecommercedata_analytics_backend.util.HdfsUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/api/test")
public class TestController {

    @Resource
    private HdfsUtil hdfsUtil;

    @Resource
    private HiveDao hiveTestService;

    /**
     * 测试HDFS连接
     */
    @GetMapping("/hdfs")
    public BaseResponse<?> testHdfsConnection() {
        boolean isConnected = hdfsUtil.testConnection();
        return ResultUtils.success("HDFS连接状态：" + (isConnected ? "成功" : "失败"));
    }

    /**
     * 测试Hive连接
     */
    @GetMapping("/hive")
    public BaseResponse<String> testHiveConnection() {
        hiveTestService.testHiveConnection();
        return ResultUtils.success("hive连接测试完成");
    }
}
