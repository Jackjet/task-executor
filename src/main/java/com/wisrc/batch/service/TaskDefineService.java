package com.wisrc.batch.service;

import com.wisrc.batch.entity.TaskDefineEntity;
import com.wisrc.batch.utils.RetMsg;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 *
 * @author hzwy23
 */
public interface TaskDefineService {
    /**
     * 查询批次中所有任务
     *
     * @param domainId
     * @param batchId
     */
    List<TaskDefineEntity> findAll(String domainId, String batchId);

    /**
     * 添加任务
     *
     * @param m
     */
    RetMsg add(TaskDefineEntity m);

    /**
     * 删除任务
     *
     * @param m
     */
    RetMsg delete(List<TaskDefineEntity> m);

    /**
     * 更新任务
     *
     * @param m
     */
    RetMsg update(TaskDefineEntity m);
}
