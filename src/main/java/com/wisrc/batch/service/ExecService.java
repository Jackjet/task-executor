package com.wisrc.batch.service;

import com.wisrc.batch.dto.BatchRunConfDto;
import com.wisrc.batch.entity.ExecLogEntity;
import com.wisrc.batch.utils.RetMsg;

/**
 * Created by hzwy23 on 2017/7/13.
 */
public interface ExecService {
    RetMsg echo(ExecLogEntity row);

    RetMsg init(BatchRunConfDto confDto);
}
