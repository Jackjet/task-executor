package com.wisrc.batch.dao.impl;

import com.wisrc.batch.dao.ExecDao;
import com.wisrc.batch.dao.impl.sql.BatchSqlText;
import com.wisrc.batch.dto.BatchRunConfDto;
import com.wisrc.batch.entity.ExecLogEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by hzwy23 on 2017/7/13.
 */
@Repository
@Slf4j
public class ExecDaoImpl implements ExecDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public int insert(ExecLogEntity row) {
        return jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_211"),
                row.getJobId(), row.getMessage(),
                row.getExecTime(), row.getSortId(),
                row.getBatchId(), row.getAsOfDate());
    }

    @Override
    public void init(BatchRunConfDto conf) {
        jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_213"), conf.getBatchId(), conf.getAsOfDate());
    }
}
