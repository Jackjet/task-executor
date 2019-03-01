package com.wisrc.batch.service.impl;

import com.wisrc.batch.dao.BatchHistoryDao;
import com.wisrc.batch.entity.BatchHistoryEntity;
import com.wisrc.batch.service.BatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/16.
 */
@Service
public class BatchHistoryServiceImpl implements BatchHistoryService {
    @Autowired
    private BatchHistoryDao batchHistoryDao;

    @Override
    public List<BatchHistoryEntity> findAll(String domainId) {
        return batchHistoryDao.findAll(domainId);
    }

}
