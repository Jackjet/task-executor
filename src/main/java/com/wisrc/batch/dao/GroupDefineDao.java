package com.wisrc.batch.dao;

import com.wisrc.batch.entity.GroupDefineEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public interface GroupDefineDao {
    List findAll(String domainId);

    int update(GroupDefineEntity m);

    String delete(List<GroupDefineEntity> m);

    int add(GroupDefineEntity m);
}
