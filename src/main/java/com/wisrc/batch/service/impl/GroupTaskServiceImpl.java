package com.wisrc.batch.service.impl;

import com.wisrc.batch.dao.GroupArgumentDao;
import com.wisrc.batch.dao.GroupTaskDao;
import com.wisrc.batch.dao.TaskArgumentDao;
import com.wisrc.batch.dto.GroupDefineDto;
import com.wisrc.batch.dto.GroupTaskDto;
import com.wisrc.batch.entity.*;
import com.wisrc.batch.service.BatchGroupService;
import com.wisrc.batch.service.GroupTaskService;
import com.wisrc.batch.utils.RetMsg;
import com.wisrc.batch.utils.SysStatus;
import com.wisrc.batch.utils.factory.RetMsgFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hzwy23 on 2017/5/25.
 */
@Service
public class GroupTaskServiceImpl implements GroupTaskService {
    private final Logger logger = LoggerFactory.getLogger(GroupTaskServiceImpl.class);
    @Autowired
    private GroupTaskDao groupTaskDao;
    @Autowired
    private BatchGroupService batchGroupService;
    @Autowired
    private TaskArgumentDao taskArgumentDao;
    @Autowired
    private GroupArgumentDao groupArgumentDao;


    @Override
    public List<GroupTaskEntity> findByBatchId(String domainId, String batchId) {
        List<GroupTaskEntity> list = groupTaskDao.findAll(domainId);

        // 查询批次中所有的任务组
        List<BatchGroupEntity> grouList = batchGroupService.findByBatchId(domainId, batchId);

        Set<String> set = new HashSet<>();

        for (BatchGroupEntity m : grouList) {
            set.add(m.getGroupId());
        }

        for (int i = 0; i < list.size(); i++) {
            String groupId = list.get(i).getGroupId();
            if (!set.contains(groupId)) {
                list.remove(i);
                i--;
            }
        }
        return list;
    }
}
