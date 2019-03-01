package com.wisrc.batch.dao.impl;

import com.wisrc.batch.dao.GroupArgumentDao;
import com.wisrc.batch.dao.impl.sql.BatchSqlText;
import com.wisrc.batch.entity.GroupArgumentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/30.
 */
@Repository
public class GroupArgumentDaoImpl implements GroupArgumentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List<GroupArgumentEntity> findAll(String domainId) {
        RowMapper<GroupArgumentEntity> rowMapper = new BeanPropertyRowMapper<>(GroupArgumentEntity.class);
        return jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_114"), rowMapper, domainId);
    }
}
