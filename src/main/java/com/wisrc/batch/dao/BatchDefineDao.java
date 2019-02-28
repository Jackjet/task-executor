package com.wisrc.batch.dao;


import com.wisrc.batch.entity.BatchDefineEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public interface BatchDefineDao {

    List findAll(String domainId);

    int getStatus(String batchId);

    int batchPagging(String batchId, String asOfDate);

    int setStatus(String batchId, int status);

    int runBatchInit(String batchId);

    int destoryBatch(String batchId, String retMsg, int status);

    int saveHistory(String batchId);

    String getBatchAsOfDate(String batchId);

    BatchDefineEntity findDetailsByBatchId(String batchId);

    void initBatchStatus();
}
