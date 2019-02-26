package com.wisrc.batch.dao;


import com.wisrc.batch.entity.ProcEntity;
import com.wisrc.batch.entity.SysConfigEntity;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/15.
 */
public interface SysConfigDao {
    List<SysConfigEntity> findAll(String domainId);

    int setValue(String domainId, String configId, String configValue);

    String getValue(String domainId, String configId);

    List<ProcEntity> getProcList();
}
