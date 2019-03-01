package com.wisrc.batch.dao.impl;

import com.wisrc.batch.dao.TaskDependencyDao;
import com.wisrc.batch.dao.impl.sql.BatchSqlText;
import com.wisrc.batch.dto.JobKeyDepDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/27.
 */
@Repository
@Slf4j
public class TaskDependencyDaoImpl implements TaskDependencyDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List<JobKeyDepDto> findById(String domainId, String batchId) {
        RowMapper<JobKeyDepDto> rowMapper = new BeanPropertyRowMapper<JobKeyDepDto>(JobKeyDepDto.class);
        return jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_215"), rowMapper, domainId, batchId);
    }
}
