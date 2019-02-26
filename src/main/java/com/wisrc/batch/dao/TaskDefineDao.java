package com.wisrc.batch.dao;

import com.wisrc.batch.entity.TaskDefineEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public interface TaskDefineDao {
    List findAll(String domainId);

    int add(TaskDefineEntity m);

    String delete(List<TaskDefineEntity> m);

    int update(TaskDefineEntity m);
}
