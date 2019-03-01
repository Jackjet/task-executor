package com.wisrc.batch.dao;

import com.wisrc.batch.entity.BatchHistoryEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/16.
 */
public interface BatchHistoryDao {
    List<BatchHistoryEntity> findAll(String domainId);
}
