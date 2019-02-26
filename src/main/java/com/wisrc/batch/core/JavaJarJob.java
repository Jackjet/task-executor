package com.wisrc.batch.core;

import com.wisrc.batch.dto.BatchRunConfDto;
import com.wisrc.batch.entity.TaskArgumentEntity;
import com.wisrc.batch.service.ArgumentService;
import com.wisrc.batch.service.JobKeyStatusService;
import com.wisrc.batch.utils.DateTime;
import com.wisrc.batch.utils.JoinCode;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by hzwy23 on 2017/7/4.
 */
public class JavaJarJob extends QuartzJobBean {
    private final Logger logger = LoggerFactory.getLogger(ExecuteJob.class);

    private String scriptPath;
    private ArgumentService argumentService;
    private JobKeyStatusService jobKeyStatusService;
    private CaptureConsole captureConsole;
    private String jobName;
    private BatchRunConfDto conf;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String jobParameters = getJobParameters();
        String cmd = getScriptFilePath(conf.getBasePath(), scriptPath);
        logger.info("开始执行时间是：{}, 脚本路径是：{}，参数是：{}", DateTime.getCurrentDateTime(), cmd, jobParameters);

        try {
            String command = new StringBuilder("java -jar ").append(cmd).append(" ").append(jobParameters).toString();
            Process process = Runtime.getRuntime().exec(command);
            captureConsole.capture(process, jobName, conf);
            if (0 == process.waitFor()) {
                logger.info("任务执行完成，任务是：{}", cmd);
                jobKeyStatusService.setJobError(jobName);
            }
        } catch (IOException | InterruptedException e) {
            jobKeyStatusService.setJobCompleted(jobName);
            captureConsole.writeLog(jobName, e.getMessage(), 0, conf);
            logger.error("Java可执行程序执行错误，程序是：{}, 参数是：{}, 错误消息是：{]", cmd, jobParameters, e.getMessage());
        }
    }

    private String getJobParameters() {
        String jobId = JoinCode.getTaskCode(jobName);
        List<TaskArgumentEntity> list = argumentService.queryArgument(jobId);
        if (list == null) {
            return "";
        }
        StringBuilder jobParameters = new StringBuilder();
        for (TaskArgumentEntity m : list) {
            String val = m.getArgValue();
            jobParameters.append(" ").append("\"").append(val).append("\"");
        }
        return jobParameters.toString();
    }

    private String getScriptFilePath(String basePath, String scriptPath) {
        if ("\\".equals(File.separator)) {
            // window 系统
            scriptPath = scriptPath.replace("/", "\\");
            return basePath + scriptPath;
        } else {
            // linux 系统
            scriptPath = scriptPath.replace("\\", "/");
            return basePath + scriptPath;
        }
    }


    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }


    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setConf(BatchRunConfDto conf) {
        this.conf = conf;
    }

    public void setArgumentService(ArgumentService argumentService) {
        this.argumentService = argumentService;
    }

    public void setJobKeyStatusService(JobKeyStatusService jobKeyStatusService) {
        this.jobKeyStatusService = jobKeyStatusService;
    }

    public void setCaptureConsole(CaptureConsole captureConsole) {
        this.captureConsole = captureConsole;
    }
}
