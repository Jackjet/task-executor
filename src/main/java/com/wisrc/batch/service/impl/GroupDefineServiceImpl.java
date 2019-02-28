package com.wisrc.batch.service.impl;

import com.wisrc.batch.dao.GroupArgumentDao;
import com.wisrc.batch.dao.GroupDefineDao;
import com.wisrc.batch.entity.GroupDefineEntity;
import com.wisrc.batch.service.GroupDefineService;
import com.wisrc.batch.utils.RetMsg;
import com.wisrc.batch.utils.SysStatus;
import com.wisrc.batch.utils.factory.RetMsgFactory;
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

    @Override
    public RetMsg update(GroupDefineEntity m) {
        try {
            int size = groupDefineDao.update(m);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "更新任务组信息失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), m);
        }
    }

    @Override
    public RetMsg delete(List<GroupDefineEntity> m) {
        try {
            String msg = groupDefineDao.delete(m);
            if ("success".equals(msg)) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "删除任务组信息失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), m);
        }
    }

    @Override
    public RetMsg add(GroupDefineEntity m) {
        try {
            int size = groupDefineDao.add(m);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "新增任务组信息失败，请联系管理员", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

}
