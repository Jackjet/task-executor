package com.wisrc.batch.dao;


import com.wisrc.batch.entity.GroupDependencyEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/27.
 */
public interface GroupDependencyDao {
    List<GroupDependencyEntity> findById(String domainId, String batchId);
}
