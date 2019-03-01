package com.wisrc.batch.dao.impl;


import com.wisrc.batch.dao.ArgumentDefineDao;
import com.wisrc.batch.dao.impl.sql.BatchSqlText;
import com.wisrc.batch.entity.ArgumentDefineEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 *
 * @author hzwy23
 */
@Repository
public class ArgumentDefineDaoImpl implements ArgumentDefineDao {
    private final Logger logger = LoggerFactory.getLogger(ArgumentDefineDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List findAll(String domainId) {
        RowMapper<ArgumentDefineEntity> rowMapper = new BeanPropertyRowMapper<ArgumentDefineEntity>(ArgumentDefineEntity.class);
        List<ArgumentDefineEntity> list = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_104"), rowMapper, domainId);
        return list;
    }
}
