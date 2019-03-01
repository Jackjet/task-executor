package com.wisrc.batch.dao.impl;

import com.wisrc.batch.dao.BatchJobStatusDao;
import com.wisrc.batch.dao.impl.sql.BatchSqlText;
import com.wisrc.batch.dto.BatchRunConfDto;
import com.wisrc.batch.utils.JoinCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by hzwy23 on 2017/6/14.
 */
@Repository
public class BatchJobStatusDaoImpl implements BatchJobStatusDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Transactional
    @Override
    public int init(BatchRunConfDto conf, Map<String, Integer> map) {
        jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_166"), conf.getBatchId(), conf.getAsOfDate());
        for (Map.Entry<String, Integer> m : map.entrySet()) {
            String suiteKey = JoinCode.getFirst(m.getKey());
            String jobKey = JoinCode.getLast(m.getKey());
            jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_167"), conf.getBatchId(), m.getKey(), m.getValue(), suiteKey, jobKey, conf.getAsOfDate());
        }
        return 1;
    }

    @Override
    public int setJobRunning(BatchRunConfDto conf, String jobId, int status) {
        return jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_190"), status, conf.getBatchId(), jobId, conf.getAsOfDate());
    }

    @Override
    public int setJobEnd(BatchRunConfDto conf, String jobId, int status) {
        return jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_191"), status, conf.getBatchId(), jobId, conf.getAsOfDate());
    }
}
