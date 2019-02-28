package com.wisrc.batch.service.impl;

import com.wisrc.batch.dao.BatchGroupDao;
import com.wisrc.batch.dto.BatchGroupDto;
import com.wisrc.batch.entity.BatchGroupEntity;
import com.wisrc.batch.entity.GroupDependencyEntity;
import com.wisrc.batch.service.BatchGroupService;
import com.wisrc.batch.utils.RetMsg;
import com.wisrc.batch.utils.SysStatus;
import com.wisrc.batch.utils.factory.RetMsgFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzwy23 on 2017/5/25.
 */
@Service
@Slf4j
public class BatchGroupServiceImpl implements BatchGroupService {

    @Autowired
    private BatchGroupDao batchGroupDao;

    @Override
    public List<BatchGroupEntity> findByBatchId(String domainId, String batchId) {
        return batchGroupDao.findAll(domainId, batchId);
    }

}
