package com.wisrc.batch.service;

import com.wisrc.batch.dto.BatchHistoryDto;
import com.wisrc.batch.entity.BatchHistoryEntity;
import com.wisrc.batch.utils.RetMsg;

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

    /**
     * 删除域中的批次历史信息
     *
     * @param list
     */
    RetMsg delete(List<BatchHistoryDto> list);
}
