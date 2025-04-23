package com.example.ecommercedata_analytics_backend.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 */
public interface FileUploadService {


    /**
     * 上传并且导入订单数据
     */
    String uploadAndImportOrders(MultipartFile file);

    /**
     * 上传并且导入用户数据
     */
    String uploadAndImportUsers(MultipartFile file);
}
