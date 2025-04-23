package com.example.ecommercedata_analytics_backend.service.impl;

import com.example.ecommercedata_analytics_backend.common.BusinessException;
import com.example.ecommercedata_analytics_backend.constant.ErrorCode;
import com.example.ecommercedata_analytics_backend.dao.HiveDao;
import com.example.ecommercedata_analytics_backend.service.FileUploadService;
import com.example.ecommercedata_analytics_backend.util.HdfsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    @Resource
    private HdfsUtil hdfsUtil;

    @Resource
    private HiveDao hiveDao;

    @Override
    public String uploadAndImportUsers(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件不能为空");
        }

        try {
            String fileName = file.getOriginalFilename();
            String hdfsPath = hdfsUtil.uploadFile(file.getInputStream(), fileName);

            // 加载数据到用户表
            hiveDao.loadDataIntoUsers(hdfsPath);

            return hdfsPath;

        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
        }
    }

    @Override
    public String uploadAndImportOrders(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件不能为空");
        }

        try {
            String fileName = file.getOriginalFilename();
            String hdfsPath = hdfsUtil.uploadFile(file.getInputStream(), fileName);

            // 设置动态分区
            hiveDao.setDynamicPartitionConfig();

            // 加载数据到临时表
            hiveDao.loadDataIntoTempOrders(hdfsPath);

            // 将临时表数据插入分区表
            hiveDao.insertTempIntoOrders();

            return hdfsPath;

        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
        }
    }
}
