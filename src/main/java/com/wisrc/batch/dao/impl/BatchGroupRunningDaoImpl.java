package com.wisrc.batch.dao.impl;


import com.wisrc.batch.dao.BatchDefineDao;
import com.wisrc.batch.dao.BatchGroupRunningDao;
import com.wisrc.batch.dao.impl.sql.BatchSqlText;
import com.wisrc.batch.entity.BatchGroupStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/17.
 */
@Repository
public class BatchGroupRunningDaoImpl implements BatchGroupRunningDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BatchDefineDao batchDefineDao;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List<BatchGroupStatusEntity> findAll(String batchId, String asOfDate) {
        RowMapper<BatchGroupStatusEntity> rowMapper = new BeanPropertyRowMapper<>(BatchGroupStatusEntity.class);
        List<BatchGroupStatusEntity> list = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_201"), rowMapper, batchId, asOfDate);
        for (BatchGroupStatusEntity bh : list) {

            Integer totalCnt = getTotalJobs(batchId, bh.getSuiteKey(), asOfDate);

            Integer completeCnt = getCompleteJobs(batchId, bh.getSuiteKey(), asOfDate);

            bh.setTotalJobsCnt(totalCnt);

            bh.setCompleteJobsCnt(completeCnt);
        }
        return list;
    }

    @Override
    public BatchGroupStatusEntity getDetails(String batchId, String suiteKey, String asOfDate) {

        RowMapper<BatchGroupStatusEntity> rowMapper = new BeanPropertyRowMapper<>(BatchGroupStatusEntity.class);
        BatchGroupStatusEntity batchGroupStatusEntity = jdbcTemplate.queryForObject(batchSqlText.getSql("sys_rdbms_205"), rowMapper, batchId, suiteKey, asOfDate);

        Integer totalCnt = getTotalJobs(batchId, suiteKey, asOfDate);

        Integer completeCnt = getCompleteJobs(batchId, suiteKey, asOfDate);

        Integer ratio = 100;

        if (totalCnt != 0) {
            ratio = 100 * completeCnt / totalCnt;
        } else {
            String statusCd = batchGroupStatusEntity.getStatus();
            if (statusCd.equals("0") || statusCd.equals("1")) {
                ratio = 0;
            }
        }

        batchGroupStatusEntity.setTotalJobsCnt(totalCnt);

        batchGroupStatusEntity.setCompleteJobsCnt(completeCnt);

        batchGroupStatusEntity.setRatio(ratio);

        return batchGroupStatusEntity;
    }

    @Override
    public Integer getRatio(String batchId, String gid, String asOfDate) {
        Integer totalJobs = getTotalJobs(batchId, gid, asOfDate);
        Integer completeJobs = getCompleteJobs(batchId, gid, asOfDate);
        if (totalJobs == 0) {
            return 100;
        }
        return 100 * completeJobs / totalJobs;
    }

    /*
     * 查询批次中所有的job总量
     * @param batchId   批次号
     * @param gid  批次配置中,任务组的id
     * @return 返回这个任务组中任务总量
     * */
    private Integer getTotalJobs(String batchId, String gid, String asOfDate) {
        return jdbcTemplate.queryForObject(batchSqlText.getSql("sys_rdbms_202"), Integer.class, batchId, gid, asOfDate);
    }

    /*
     * 查询批次中已经完成的job总量
     * @param batchId   批次号
     * @param gid  批次配置中,任务组的id
     * @return 返回这个任务组中已经完成的任务量
     * */
    private Integer getCompleteJobs(String uuid, String gid, String asOfDate) {
        return jdbcTemplate.queryForObject(batchSqlText.getSql("sys_rdbms_203"), Integer.class, uuid, gid, asOfDate);
    }
}
