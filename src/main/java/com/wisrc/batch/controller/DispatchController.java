package com.wisrc.batch.controller;

import com.wisrc.batch.core.QuartzSchedulerManager;
import com.wisrc.batch.dto.BatchRunConfDto;
import com.wisrc.batch.service.BatchDefineService;
import com.wisrc.batch.service.ExecService;
import com.wisrc.batch.utils.BatchStatus;
import com.wisrc.batch.utils.Hret;
import com.wisrc.batch.utils.RetMsg;
import com.wisrc.batch.utils.SysStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hzwy23 on 2017/5/23.
 */
@Controller
@Scope("prototype")
@Api(description ="批次调度-批次启动管理")
@Slf4j
public class DispatchController {

    @Autowired
    private QuartzSchedulerManager quartzSchedulerManager;

    @Autowired
    private BatchDefineService batchDefineService;

    @Autowired
    private ExecService execService;

    @RequestMapping(value = "/v1/dispatch/start", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "启动批次", notes = "启动批次时，需要两个参数，一个是域编码，另一个是批次编码")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "domainId", value = "域编码"),
            @ApiImplicitParam(required = true, name = "batchId", value = "批次编码"),
    })
    public String start(@RequestParam(value = "domainId") String domainId,
                        @RequestParam(value = "batchId") String batchId,
                        HttpServletResponse response) {

        if (domainId == null || batchId == null) {
            response.setStatus(421);
            log.info("batch id or domain jobKey is null ,batch jobKey is: {}, domain jobKey is: {}", batchId, domainId);
            return Hret.error(421, "domain_id is empty or batch_id is empty", null);
        }

        if (BatchStatus.BATCH_STATUS_RUNNING == batchDefineService.getStatus(batchId)) {
            response.setStatus(421);
            log.info("批次正在运行中，无法重新启动, 批次号是：{}", batchId);
            return Hret.error(421, "批次正在运行中", null);
        }

        BatchRunConfDto bconf = batchDefineService.initConf(batchId, domainId);
        RetMsg retMsg = execService.init(bconf);
        if (!retMsg.checkCode()) {
            log.info(retMsg.getMessage());
            response.setStatus(retMsg.getCode());
            return Hret.error(retMsg);
        }

        retMsg = batchDefineService.runBatchInit(batchId);
        if (SysStatus.SUCCESS_CODE != retMsg.getCode()) {
            batchDefineService.setStatus(batchId, BatchStatus.BATCH_STATUS_ERROR);
            log.info(retMsg.toString());
            response.setStatus(426);
            return Hret.error(retMsg);
        }
        log.debug("初始化修改批次状态信息完成，批次号是：{}", batchId);

        // 进度调度依赖关系管理
        // 根据依赖关系,开启任务触发器
        try {
            quartzSchedulerManager.createJobSchedulerService(bconf);
        } catch (Exception e) {
            e.printStackTrace();
            batchDefineService.setStatus(batchId, BatchStatus.BATCH_STATUS_ERROR);
            return Hret.error(SysStatus.ERROR_CODE, "启动调度器失败", e.getMessage());
        }
        log.debug("调度器创建成功，批次号是：{}", batchId);

        quartzSchedulerManager.schedulerStart();
        log.info("批次初始化完成，调度服务已启动，批次号是：{}", batchId);
        return Hret.success(SysStatus.SUCCESS_CODE, "start batch successfully. batch id is :" + batchId, null);
    }
}
