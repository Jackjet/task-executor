package com.wisrc.batch.service;

import com.wisrc.batch.dto.GroupDefineDto;
import com.wisrc.batch.dto.GroupTaskDto;
import com.wisrc.batch.entity.GroupTaskEntity;
import com.wisrc.batch.entity.TaskArgumentEntity;
import com.wisrc.batch.entity.TaskDependencyEntity;
import com.wisrc.batch.utils.RetMsg;

import java.util.List;
import java.util.Set;

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
