package com.wisrc.batch.service.impl;

import com.wisrc.batch.dao.TaskArgumentDao;
import com.wisrc.batch.dao.TaskDefineDao;
import com.wisrc.batch.entity.GroupTaskEntity;
import com.wisrc.batch.entity.TaskDefineEntity;
import com.wisrc.batch.service.GroupTaskService;
import com.wisrc.batch.service.TaskDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hzwy23 on 2017/5/24.
 */
@Service
public class TaskDefineServiceImpl implements TaskDefineService {
    @Autowired
    private TaskDefineDao dispatchTaskDefineDao;

    @Autowired
    private GroupTaskService groupTaskService;

    @Autowired
    private TaskArgumentDao taskArgumentDao;

    @Override
    public List<TaskDefineEntity> findAll(String domainId, String batchId) {
        List<TaskDefineEntity> list = dispatchTaskDefineDao.findAll(domainId);
        List<GroupTaskEntity> groupTaskEntityList = groupTaskService.findByBatchId(domainId, batchId);

        Set<String> set = new HashSet<String>();

        for (GroupTaskEntity m : groupTaskEntityList) {
            set.add(m.getTaskId());
        }

        for (int i = 0; i < list.size(); i++) {
            String taskId = list.get(i).getTaskId();
            if (!set.contains(taskId)) {
                list.remove(i);
                i--;
            }
        }

        return list;
    }

}
