package com.wisrc.batch.service;

import com.wisrc.batch.entity.GroupDefineEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/1.
 *
 * @author hzwy23
 */
public interface GroupDefineService {
    /**
     * 查询指定你批次中所有任务
     *
     * @param domainId
     */
    List<GroupDefineEntity> findAll(String domainId);


}
