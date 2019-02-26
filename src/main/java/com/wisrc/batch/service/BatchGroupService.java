package com.wisrc.batch.service;

import com.wisrc.batch.dto.BatchGroupDto;
import com.wisrc.batch.entity.BatchGroupEntity;
import com.wisrc.batch.entity.GroupDependencyEntity;
import com.wisrc.batch.utils.RetMsg;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/25.
 *
 * @author hzwy23
 */
public interface BatchGroupService {
    /**
     * 查询批次中所有的任务组
     *
     * @param domainId
     * @param batchId
     */
    List<BatchGroupEntity> findByBatchId(String domainId, String batchId);

    /**
     * 查询批次中任务组列表信息
     *
     * @param batchId
     */
    List<BatchGroupEntity> getGroup(String batchId);

    /**
     * 添加任务组
     *
     * @param list
     */
    RetMsg addGroup(List<BatchGroupDto> list);

    /**
     * 删除任务组
     *
     * @param list
     */
    RetMsg deleteGroup(List<BatchGroupDto> list);

    /**
     * 获取批次中，任务组的依赖
     *
     * @param batchId  批次编码
     * @param suiteKey 批次任务组关系中，任务组的id号
     */
    List<BatchGroupEntity> getAvaiableDependencySuite(String batchId, String suiteKey);

    /**
     * 查询某一个suite的所有上级依赖
     *
     * @param suiteKey
     */
    List<BatchGroupEntity> getDependencySuite(String suiteKey);

    /**
     * 删除任务组依赖
     *
     * @param uuid 任务组依赖配置中唯一id
     */
    RetMsg deleteGroupDependency(String uuid);

    /**
     * 给任务组新增依赖
     */
    RetMsg addGroupDependency(List<GroupDependencyEntity> list);
}
