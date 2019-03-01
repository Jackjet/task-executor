package com.wisrc.batch.service.impl;

import com.wisrc.batch.dao.BatchGroupRunningDao;
import com.wisrc.batch.entity.BatchGroupStatusEntity;
import com.wisrc.batch.service.BatchGroupRunningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 */
@Service
public class BatchGroupRunningServiceImpl implements BatchGroupRunningService {
    @Autowired
    private BatchGroupRunningDao batchGroupRunningDao;

    @Override
    public List<BatchGroupStatusEntity> findAll(String batchId, String asOfDate) {
        return batchGroupRunningDao.findAll(batchId, asOfDate);
    }
}
