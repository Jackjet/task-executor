package com.wisrc.batch.dao;

import com.wisrc.batch.entity.GroupArgumentEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/30.
 */
public interface GroupArgumentDao {
    List<GroupArgumentEntity> findAll(String domainId);
}
