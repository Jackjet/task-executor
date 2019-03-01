package com.wisrc.batch.service;

import com.wisrc.batch.entity.GroupTaskEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/25.
 *
 * @author hzwy23
 */
public interface GroupTaskService {
    /**
     * 查询某个域中，某个批次所有任务
     *
     * @param domainId
     * @param batchId
     * @return List 批次中，所有的任务
     */
    List<GroupTaskEntity> findByBatchId(String domainId, String batchId);

}
