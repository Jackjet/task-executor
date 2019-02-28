package com.wisrc.batch.service.impl;

import com.wisrc.batch.dao.ArgumentDefineDao;
import com.wisrc.batch.entity.ArgumentDefineEntity;
import com.wisrc.batch.service.ArgumentDefineService;
import com.wisrc.batch.utils.RetMsg;
import com.wisrc.batch.utils.SysStatus;
import com.wisrc.batch.utils.factory.RetMsgFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/7/17.
 */
@Service
public class ArgumentDefineServiceImpl implements ArgumentDefineService {

    // 参数定义
    @Autowired
    private ArgumentDefineDao argumentDefineDao;

    @Override
    public List<ArgumentDefineEntity> findAll(String domainID) {
        return argumentDefineDao.findAll(domainID);
    }

}
