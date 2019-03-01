package com.wisrc.batch.dao;


import com.wisrc.batch.entity.BatchDefineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hzwy23 on 2017/5/24.
 */
@Repository
public interface BatchDefineDao extends JpaRepository<BatchDefineEntity, String>{

    @Query(value = "select batch_status from dispatch_batch_define where batch_id = ?1", nativeQuery = true)
    Integer getStatus(String batchId);

    @Modifying
    @Transactional
    @Query(value = "update dispatch_batch_define set as_of_date = ?2, batch_status = '1',start_date = now(), ret_msg = null, end_date = null where batch_id = ?1 and batch_status = '2'", nativeQuery = true)
    Integer batchPagging(String batchId, String asOfDate);

    @Query(value = "update dispatch_batch_define set batch_status = ?2 where batch_id = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    int setStatus(String batchId, int status);

    @Modifying
    @Transactional
    @Query(value = "update dispatch_batch_define set batch_status = '1',start_date = now(), ret_msg = null, end_date = null where batch_id = ?1 and complete_date >= as_of_date", nativeQuery = true)
    int runBatchInit(String batchId);

    @Query(value = "update dispatch_batch_define set ret_msg = ?2, end_date = now(), batch_status = ?3 where batch_id = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    int destoryBatch(String batchId, String retMsg, int status);

    @Modifying
    @Transactional
    @Query(value = "insert into dispatch_batch_history(sid,code_number,batch_id,batch_desc,batch_status,as_of_date,start_time,end_time,ret_msg,domain_id) select ?1,code_number,batch_id,batch_desc,batch_status,as_of_date,start_date,end_date,ret_msg,domain_id from dispatch_batch_define where batch_id = ?2", nativeQuery = true)
    void saveBatchHistory(String uuid, String batchId);

    @Modifying
    @Transactional
    @Query(value = "insert into dispatch_batch_group_history(sid,suite_key,status,start_time,end_time) select ?1,suite_key,status,start_time,end_time from dispatch_batch_group_status where batch_id = ?2 and as_of_date = ?3", nativeQuery = true)
    void saveBatchGroupHistory(String uuid,String batchId, String asOfDate);

    @Modifying
    @Transactional
    @Query(value = "insert into dispatch_job_execute_history(sid,job_id,message,exec_time,sort_id,batch_id) select ?1,job_id,message,exec_time,sort_id,batch_id from dispatch_job_execute_log where batch_id = ?2 and as_of_date = ?3", nativeQuery = true)
    void saveBatchJobHistory(String uuid, String batchId, String asOfDate);

    @Modifying
    @Transactional
    @Query(value = "insert into dispatch_batch_job_history(sid,job_id,status,start_time,end_time,suite_key,job_key) select ?1,job_id,status,start_time,end_time,suite_key,job_key from dispatch_batch_job_status where batch_id = ?2 and as_of_date = ?3", nativeQuery = true)
    void saveBatchLogHistory(String uuid, String batchId, String asOfDate);

    @Query(value = "select as_of_date from dispatch_batch_define where batch_id = ?1 and complete_date >= as_of_date", nativeQuery = true)
    String getBatchAsOfDate(String batchId);

    /**
     * 停止所有正在运行中的批次
     * 批次状态，请参考：
     * @see com.wisrc.batch.constant.BatchStatusConstant
     * */
    @Query(value = "update dispatch_batch_define set batch_status = '4' where batch_status = '1'", nativeQuery = true)
    @Modifying
    @Transactional
    void initBatchStatus();


    BatchDefineEntity findByBatchId(String batchId);
}
