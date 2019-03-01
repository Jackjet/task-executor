package com.wisrc.batch.dao.impl;

import com.wisrc.batch.dao.GroupDefineDao;
import com.wisrc.batch.dao.impl.sql.BatchSqlText;
import com.wisrc.batch.entity.GroupDefineEntity;
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
public class GroupDefineDaoImpl implements GroupDefineDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List findAll(String domainId) {
        RowMapper<GroupDefineEntity> rowMapper = new BeanPropertyRowMapper<GroupDefineEntity>(GroupDefineEntity.class);
        return jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_108"), rowMapper, domainId);
    }

}
