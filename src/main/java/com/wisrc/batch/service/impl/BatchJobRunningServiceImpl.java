package com.wisrc.batch.service.impl;

import com.wisrc.batch.dao.BatchJobRunningDao;
import com.wisrc.batch.entity.BatchJobStatusEntity;
import com.wisrc.batch.service.BatchJobRunningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 */
@Service
public class BatchJobRunningServiceImpl implements BatchJobRunningService {
    @Autowired
    private BatchJobRunningDao batchJobRunningDao;

    @Override
    public List<BatchJobStatusEntity> findAll(String batchId, String suiteKey, String asOfDate) {
        return batchJobRunningDao.findAll(batchId, suiteKey, asOfDate);
    }
}
