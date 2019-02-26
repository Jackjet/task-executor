package com.wisrc.batch.dao.impl;

import com.wisrc.batch.dao.BatchGroupHistoryDao;
import com.wisrc.batch.dao.impl.sql.BatchSqlText;
import com.wisrc.batch.entity.BatchGroupHistoryEntity;
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
public class BatchGroupHistoryDaoImpl implements BatchGroupHistoryDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List<BatchGroupHistoryEntity> findAll(String uuid) {
        RowMapper<BatchGroupHistoryEntity> rowMapper = new BeanPropertyRowMapper<>(BatchGroupHistoryEntity.class);
        List<BatchGroupHistoryEntity> list = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_197"), rowMapper, uuid);
        for (BatchGroupHistoryEntity bh : list) {
            String gid = bh.getSuiteKey();
            Integer totalCnt = getTotalJobs(uuid, gid);
            Integer completeCnt = getCompleteJobs(uuid, gid);
            bh.setTotalJobsCnt(totalCnt);
            bh.setCompleteJobsCnt(completeCnt);
        }
        return list;
    }

    private Integer getTotalJobs(String uuid, String gid) {
        return jdbcTemplate.queryForObject(batchSqlText.getSql("sys_rdbms_198"), Integer.class, uuid, gid);
    }

    private Integer getCompleteJobs(String uuid, String gid) {
        return jdbcTemplate.queryForObject(batchSqlText.getSql("sys_rdbms_199"), Integer.class, uuid, gid);
    }

}
