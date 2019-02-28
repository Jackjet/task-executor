package com.wisrc.batch.service;

import com.wisrc.batch.entity.BatchGroupStatusEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 *
 * @author hzwy23
 */
public interface BatchGroupRunningService {
    /**
     * 查询运行中的批次，任务组运行信息
     *
     * @param batchId
     * @return List 批次中任务组的运行信息列表
     */
    List<BatchGroupStatusEntity> findAll(String batchId, String asOfDate);


    /**
     * 查询批次中任务组的详细信息
     *
     * @param batchId
     * @param suiteKey
     * @param asOfDate
     * @return 任务组的详细信息
     */
    BatchGroupStatusEntity getDetails(String batchId, String suiteKey, String asOfDate);
}
