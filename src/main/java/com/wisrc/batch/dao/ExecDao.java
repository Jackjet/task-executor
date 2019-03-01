package com.wisrc.batch.dao;

import com.wisrc.batch.dto.BatchRunConfDto;
import com.wisrc.batch.entity.ExecLogEntity;

/**
 * Created by hzwy23 on 2017/7/13.
 */
public interface ExecDao {
    int insert(ExecLogEntity row);

    void init(BatchRunConfDto confDto);
}
