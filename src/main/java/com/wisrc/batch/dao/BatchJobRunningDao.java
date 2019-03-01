package com.wisrc.batch.dao;

import com.wisrc.batch.entity.BatchJobStatusEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 */
public interface BatchJobRunningDao {
    List<BatchJobStatusEntity> findAll(String batchId, String suiteKey, String asOfDate);
}
