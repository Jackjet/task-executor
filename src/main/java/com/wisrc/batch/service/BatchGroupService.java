package com.wisrc.batch.service;

import com.wisrc.batch.dto.BatchGroupDto;
import com.wisrc.batch.entity.BatchGroupEntity;
import com.wisrc.batch.entity.GroupDependencyEntity;
import com.wisrc.batch.utils.RetMsg;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/25.
 *
 * @author hzwy23
 */
public interface BatchGroupService {
    /**
     * 查询批次中所有的任务组
     *
     * @param domainId
     * @param batchId
     */
    List<BatchGroupEntity> findByBatchId(String domainId, String batchId);

}
