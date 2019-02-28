package com.wisrc.batch.service;

import com.wisrc.batch.entity.GroupDefineEntity;
import com.wisrc.batch.utils.RetMsg;

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

    /**
     * 更新任务组信息
     *
     * @param vo
     */
    RetMsg update(GroupDefineEntity vo);

    /**
     * 删除任务组
     *
     * @param vo
     */
    RetMsg delete(List<GroupDefineEntity> vo);

    /**
     * 新增任务组
     *
     * @param vo
     */
    RetMsg add(GroupDefineEntity vo);
}
