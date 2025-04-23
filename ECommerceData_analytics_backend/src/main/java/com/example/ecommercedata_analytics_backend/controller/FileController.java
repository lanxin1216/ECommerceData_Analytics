package com.example.ecommercedata_analytics_backend.controller;

import com.example.ecommercedata_analytics_backend.common.BaseResponse;
import com.example.ecommercedata_analytics_backend.common.ResultUtils;
import com.example.ecommercedata_analytics_backend.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/file")
@Slf4j
public class FileController {

    @Resource
    private FileUploadService fileUploadService;

    @PostMapping("/upload/users")
    public BaseResponse<String> uploadUsers(@RequestParam("file") MultipartFile file) {
        String hdfsPath = fileUploadService.uploadAndImportUsers(file);
        return ResultUtils.success(hdfsPath, "上传并导入成功");
    }

    @PostMapping("/upload/orders")
    public BaseResponse<String> uploadOrders(@RequestParam("file") MultipartFile file) {
        String hdfsPath = fileUploadService.uploadAndImportOrders(file);
        return ResultUtils.success(hdfsPath, "上传并导入成功");
    }


}