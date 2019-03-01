package com.wisrc.batch.dao.impl;

import com.wisrc.batch.dao.TaskArgumentDao;
import com.wisrc.batch.dao.impl.sql.BatchSqlText;
import com.wisrc.batch.entity.TaskArgumentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 */
@Repository
public class TaskArgumentDaoImpl implements TaskArgumentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List findAll(String domainId) {
        RowMapper<TaskArgumentEntity> rowMapper = new BeanPropertyRowMapper<TaskArgumentEntity>(TaskArgumentEntity.class);
        List list = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_110"), rowMapper, domainId);
        return list;
    }

}
