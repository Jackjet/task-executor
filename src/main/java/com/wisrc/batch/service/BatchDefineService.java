package com.wisrc.batch.service;

import com.wisrc.batch.dto.BatchRunConfDto;
import com.wisrc.batch.utils.RetMsg;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by hzwy23 on 2017/6/1.
 *
 * @author hzwy23
 */
public interface BatchDefineService {

    /**
     * 查询批次状态
     *
     * @param batchId
     * @return int 返回批次状态值
     */
    int getStatus(String batchId);

    /**
     * 设置批次状态值
     *
     * @param batchId
     * @param status
     * @return RetMsg 返回更新批次状态的操作结果
     */
    RetMsg setStatus(String batchId, int status);

    /**
     * 启动批次，初始化批次状态信息
     *
     * @param batchId 批次编号
     * @return RetMsg
     */
    RetMsg runBatchInit(String batchId);

    /**
     * 批次翻页，检查批次是否满足翻页条件
     *
     * @param conf 批次编号
     */
    RetMsg batchPagging(BatchRunConfDto conf);


    /**
     * 销毁批次
     *
     * @param batchId 批次编号
     * @param retMsg  批次销毁时返回信息
     * @param Status  批次销毁时状态
     * @return RetMsg
     */
    RetMsg destoryBatch(String batchId, String retMsg, int Status);

    /**
     * 批次运行完成后，保留批次的历史信息
     *
     * @param batchId 批次编号
     */
    void saveHistory(String batchId);

    /**
     * 初始化批次运行时的参数信息
     *
     * @param batchId
     * @param domainId
     */
    BatchRunConfDto initConf(String batchId, String domainId);


    void initBatchStatus();

}
