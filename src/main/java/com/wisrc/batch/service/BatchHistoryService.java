package com.wisrc.batch.service;

import com.wisrc.batch.entity.BatchHistoryEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/16.
 *
 * @author hzwy23
 */
public interface BatchHistoryService {
    /**
     * 查询域中所有的批次历史信息
     *
     * @param domainId
     */
    List<BatchHistoryEntity> findAll(String domainId);
}
