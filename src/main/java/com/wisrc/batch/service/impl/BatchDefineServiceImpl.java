package com.wisrc.batch.service.impl;

import com.wisrc.batch.dao.BatchArgumentDao;
import com.wisrc.batch.dao.BatchDefineDao;
import com.wisrc.batch.dao.BatchJobStatusDao;
import com.wisrc.batch.dto.BatchArgumentDto;
import com.wisrc.batch.dto.BatchMonitoringDto;
import com.wisrc.batch.dto.BatchRunConfDto;
import com.wisrc.batch.entity.BatchArgumentEntiry;
import com.wisrc.batch.entity.BatchDefineEntity;
import com.wisrc.batch.service.BatchDefineService;
import com.wisrc.batch.service.SysConfigService;
import com.wisrc.batch.utils.BatchStatus;
import com.wisrc.batch.utils.RetMsg;
import com.wisrc.batch.utils.SysStatus;
import com.wisrc.batch.utils.factory.RetMsgFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by hzwy23 on 2017/6/1.
 */
@Service
public class BatchDefineServiceImpl implements BatchDefineService {
    private final Logger logger = LoggerFactory.getLogger(BatchDefineServiceImpl.class);
    @Autowired
    private BatchDefineDao batchDefineDao;

    @Autowired
    private BatchArgumentDao batchArgumentDao;

    @Autowired
    private BatchJobStatusDao batchJobStatusDao;

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public List<BatchDefineEntity> findAll(String domainId) {
        return batchDefineDao.findAll(domainId);
    }

    @Override
    public int getStatus(String batchId) {
        return batchDefineDao.getStatus(batchId);
    }

    @Override
    public RetMsg setStatus(String batchId, int status) {
        try {
            int size = batchDefineDao.setStatus(batchId, status);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "设置批次状态信息失败，请联系管理员", batchId);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), batchId);
        }
    }

    @Override
    public RetMsg runBatchInit(String batchId) {
        try {
            int size = batchDefineDao.runBatchInit(batchId);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "批次初始化失败，批次无法启动", null);
        } catch (Exception e) {
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), null);
        }
    }

    @Override
    public RetMsg batchPagging(BatchRunConfDto conf) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        Date completeDate = null;
        try {
            date = sdf.parse(conf.getAsOfDate());
            completeDate = sdf.parse(conf.getCompleteDate());

        } catch (ParseException e) {
            e.printStackTrace();
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "批次日志格式不正确，退出翻页执行操作。", conf);
        }
        Integer freq = Integer.parseInt(conf.getPaggingFreq());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String dateAddVal;
        switch (conf.getPaggingFreqMult()) {
            case "MI":
                calendar.add(Calendar.MINUTE, freq);
                dateAddVal = sdf.format(calendar.getTime());
                break;
            case "H":
                calendar.add(Calendar.HOUR, freq);
                dateAddVal = sdf.format(calendar.getTime());
                break;
            case "D":
                calendar.add(Calendar.DATE, freq);
                dateAddVal = sdf.format(calendar.getTime());
                break;
            case "W":
                calendar.add(Calendar.DATE, 7 * freq);
                dateAddVal = sdf.format(calendar.getTime());
                break;
            case "M":
                calendar.add(Calendar.MONTH, freq);
                dateAddVal = sdf.format(calendar.getTime());
                break;
            case "Q":
                calendar.add(Calendar.MONDAY, 3 * freq);
                dateAddVal = sdf.format(calendar.getTime());
                break;
            case "Y":
                calendar.add(Calendar.YEAR, freq);
                dateAddVal = sdf.format(calendar.getTime());
                break;
            default:
                return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "执行频率单位不正确，退出批次调度", conf);
        }

        if (completeDate.before(calendar.getTime())) {
            return RetMsgFactory.getRetMsg(SysStatus.COMPLETED, "批次运行到终止日期，批次调度正常退出服务", conf);
        }

        // 获取批次现在的状态信息
        int status = batchDefineDao.getStatus(conf.getBatchId());
        if (BatchStatus.BATCH_STATUS_COMPLETED != status) {
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "批次状态不是完成状态，无法继续翻页执行，退出服务", conf);
        }

        try {
            int size = batchDefineDao.batchPagging(conf.getBatchId(), dateAddVal);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "批次翻页执行失败，请联系管理员", null);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), conf.getBatchId());
        }
    }

    @Override
    public RetMsg destoryBatch(String batchId, String retMsg, int status) {
        try {
            int size = batchDefineDao.destoryBatch(batchId, retMsg, status);
            if (1 == size) {
                return RetMsgFactory.getRetMsg(SysStatus.SUCCESS_CODE, "success", null);
            }
            return RetMsgFactory.getRetMsg(SysStatus.ERROR_CODE, "销毁批次失败，请联系管理员", batchId);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return RetMsgFactory.getRetMsg(SysStatus.EXCEPTION_ERROR_CODE, e.getMessage(), batchId);
        }
    }

    @Override
    public void saveHistory(String batchId) {
        try {
            batchDefineDao.saveHistory(batchId);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public BatchRunConfDto initConf(String batchId, String domainId) {
        BatchDefineEntity bde = batchDefineDao.findDetailsByBatchId(batchId);
        String basePath = sysConfigService.getValue(domainId, "CONF0001");
        String redisSwitch = sysConfigService.getValue(domainId, "CONF0002");
        BatchRunConfDto batchRunConfDto = new BatchRunConfDto();
        batchRunConfDto.setBatchId(batchId);
        batchRunConfDto.setDomainId(domainId);
        batchRunConfDto.setAsOfDate(bde.getAsOfDate());
        batchRunConfDto.setCompleteDate(bde.getCompleteDate());
        batchRunConfDto.setPaggingFreq(bde.getPaggingFreq());
        batchRunConfDto.setPaggingFreqMult(bde.getPaggingFreqMult());
        batchRunConfDto.setBasePath(basePath);
        batchRunConfDto.setRedisSwitch(redisSwitch);
        logger.debug(batchRunConfDto.toString());
        return batchRunConfDto;
    }

    @Override
    public void initBatchStatus() {
        batchDefineDao.initBatchStatus();
    }
}
