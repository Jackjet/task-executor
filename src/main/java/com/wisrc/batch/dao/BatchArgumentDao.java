package com.wisrc.batch.dao;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 *
 * @author hzwy23
 */
public interface BatchArgumentDao {
    /**
     * 查询某一个域中，某一个批次中所有任务包含的参数信息
     *
     * @param domainId 域编码
     * @param batchId  批次编码
     */
    List findAll(String domainId, String batchId);

}
