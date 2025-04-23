package com.example.ecommercedata_analytics_backend.service.impl;

import com.example.ecommercedata_analytics_backend.dao.GetDataSetDao;
import com.example.ecommercedata_analytics_backend.service.GetDataSetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GetDataSetServiceImpl implements GetDataSetService {

    @Resource
    private GetDataSetDao getDataSetDao;

    @Override
    public List<Map<String, Object>> getUsers(int page, int pageSize) {
        return getDataSetDao.getUsers(page, pageSize);
    }

    @Override
    public List<Map<String, Object>> getOrderSample() {
        return getDataSetDao.getOrderSample();
    }

}
