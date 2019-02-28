package com.wisrc.batch.dao.impl;

import com.wisrc.batch.dao.BatchArgumentDao;
import com.wisrc.batch.dao.impl.sql.BatchSqlText;
import com.wisrc.batch.entity.BatchArgumentEntiry;
import com.wisrc.batch.utils.TimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/24.
 *
 * @author hzwy23
 */
@Repository
public class BatchArgumentDaoImpl implements BatchArgumentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BatchSqlText batchSqlText;

    @Override
    public List findAll(String domainId, String batchId) {
        RowMapper<BatchArgumentEntiry> rowMapper = new BeanPropertyRowMapper<BatchArgumentEntiry>(BatchArgumentEntiry.class);

        // 获取固定参数,任务参数,任务组参数
        List<BatchArgumentEntiry> list = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_163"), rowMapper, domainId, batchId);

        //获取批次类型参数
        List<BatchArgumentEntiry> list2 = jdbcTemplate.query(batchSqlText.getSql("sys_rdbms_164"), rowMapper, domainId, batchId);

        return bindAsofdate(list, list2);
    }

    private List<BatchArgumentEntiry> bindAsofdate(List<BatchArgumentEntiry> ret, List<BatchArgumentEntiry> source) {
        for (BatchArgumentEntiry m : source) {
            // 绑定批次日期
            if ("1".equals(m.getBindAsOfDate())) {
                String asOfDate = jdbcTemplate.queryForObject(batchSqlText.getSql("sys_rdbms_157"),
                        String.class,
                        m.getBatchId());
                m.setArgValue(asOfDate);
            }
            ret.add(m);
        }
        return ret;
    }
}
