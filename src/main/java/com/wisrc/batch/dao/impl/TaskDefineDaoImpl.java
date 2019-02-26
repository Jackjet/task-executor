package com.wisrc.batch.dao.impl;


import com.wisrc.batch.dao.TaskDefineDao;
import com.wisrc.batch.dao.impl.sql.BatchSqlText;
import com.wisrc.batch.entity.TaskDefineEntity;
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
public class TaskDefineDaoImpl implements TaskDefineDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List findAll(String domainId) {
        RowMapper<TaskDefineEntity> rowMapper = new BeanPropertyRowMapper<TaskDefineEntity>(TaskDefineEntity.class);
        return jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_111"), rowMapper, domainId);
    }

    @Override
    public int add(TaskDefineEntity m) {
        return jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_125"),
                m.getTaskId(),
                m.getCodeNumber(),
                m.getTaskDesc(),
                m.getTaskType(),
                m.getCreateUser(),
                m.getModifyUser(),
                m.getDomainId(),
                m.getScriptFile());
    }

    @Transactional
    @Override
    public String delete(List<TaskDefineEntity> m) {
        for (TaskDefineEntity l : m) {
            if (1 != jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_127"), l.getTaskId(), l.getDomainId())) {
                return "删除[" + l.getCodeNumber() + "]失败";
            }
        }
        return "success";
    }

    @Override
    public int update(TaskDefineEntity m) {
        return jdbcTemplate.update(batchSqlText.getSql("sys_rdbms_126"),
                m.getTaskDesc(),
                m.getTaskType(),
                m.getScriptFile(),
                m.getTaskId(),
                m.getDomainId());
    }
}
