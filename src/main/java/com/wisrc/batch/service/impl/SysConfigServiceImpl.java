package com.wisrc.batch.service.impl;

import com.wisrc.batch.dao.SysConfigDao;
import com.wisrc.batch.entity.ProcEntity;
import com.wisrc.batch.entity.SysConfigEntity;
import com.wisrc.batch.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/15.
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Autowired
    private SysConfigDao sysConfigDao;

    @Override
    public List<SysConfigEntity> findAll(String domainId) {
        return sysConfigDao.findAll(domainId);
    }

    @Override
    public String getValue(String domainId, String configId) {
        return sysConfigDao.getValue(domainId, configId);
    }

}
