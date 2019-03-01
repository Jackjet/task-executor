package com.wisrc.batch.dao;


import com.wisrc.batch.entity.GroupTaskEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public interface GroupTaskDao {
    List findAll(String domainId);

    List<GroupTaskEntity> getTaskDependency(String id);

}
