package com.wisrc.batch.service.impl;

import com.wisrc.batch.dao.ExecDao;
import com.wisrc.batch.dto.BatchRunConfDto;
import com.wisrc.batch.entity.ExecLogEntity;
import com.wisrc.batch.service.ExecService;
import com.wisrc.batch.utils.RetMsg;
import com.wisrc.batch.utils.SysStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * Created by hzwy23 on 2017/7/13.
 */
@Service
@Slf4j
public class ExecServiceImpl implements ExecService {

    @Autowired
    private ExecDao execDao;

    @Override
    public RetMsg echo(ExecLogEntity row) {
        try {
            int size = execDao.insert(row);
            if (1 == size) {
                return new RetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return new RetMsg(SysStatus.ERROR_CODE, "写入执行信息失败", null);
        } catch (Exception e) {
            return new RetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), row.toString());
        }
    }

    @Override
    public RetMsg init(BatchRunConfDto confDto) {
        try {
            execDao.init(confDto);
            return new RetMsg(SysStatus.SUCCESS_CODE, "success", null);
        } catch (DataAccessException e) {
            return new RetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), confDto.getBatchId());
        }
    }
}
