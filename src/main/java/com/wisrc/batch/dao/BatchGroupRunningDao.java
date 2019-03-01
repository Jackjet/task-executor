package com.wisrc.batch.dao;


import com.wisrc.batch.entity.BatchGroupStatusEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 */
public interface BatchGroupRunningDao {
    List<BatchGroupStatusEntity> findAll(String domainId, String asOfDate);
}
