package com.wisrc.batch.service.impl;

import com.wisrc.batch.dao.GroupArgumentDao;
import com.wisrc.batch.dao.GroupDefineDao;
import com.wisrc.batch.entity.GroupDefineEntity;
import com.wisrc.batch.service.GroupDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hzwy23 on 2017/6/1.
 */
@Service
public class GroupDefineServiceImpl implements GroupDefineService {
    @Autowired
    private GroupDefineDao groupDefineDao;
    @Autowired
    private GroupArgumentDao groupArgumentDao;

    @Override
    public List<GroupDefineEntity> findAll(String domainId) {
        return groupDefineDao.findAll(domainId);
    }

}
