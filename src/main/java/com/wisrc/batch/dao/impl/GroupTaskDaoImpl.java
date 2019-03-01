package com.wisrc.batch.dao.impl;

import com.wisrc.batch.dao.GroupTaskDao;
import com.wisrc.batch.dao.impl.sql.BatchSqlText;
import com.wisrc.batch.entity.GroupTaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 */
@Repository
public class GroupTaskDaoImpl implements GroupTaskDao {
    private final Logger logger = LoggerFactory.getLogger(GroupTaskDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List findAll(String domainId) {
        RowMapper<GroupTaskEntity> rowMapper = new BeanPropertyRowMapper<GroupTaskEntity>(GroupTaskEntity.class);
        List list = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_109"), rowMapper, domainId);
        return list;
    }

    @Transactional
    @Override
    public List<GroupTaskEntity> getTaskDependency(String id) {
        RowMapper<GroupTaskEntity> rowMapper = new BeanPropertyRowMapper<>(GroupTaskEntity.class);
        List<GroupTaskEntity> list = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_134"), rowMapper, id);
        return list;
    }

}
