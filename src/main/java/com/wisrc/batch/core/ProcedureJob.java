package com.wisrc.batch.core;

import com.wisrc.batch.entity.TaskArgumentEntity;
import com.wisrc.batch.service.ArgumentService;
import com.wisrc.batch.service.JobKeyStatusService;
import com.wisrc.batch.utils.DateTime;
import com.wisrc.batch.utils.JoinCode;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * Created by hzwy23 on 2017/5/31.
 */
public class ProcedureJob extends QuartzJobBean {
    private final Logger logger = LoggerFactory.getLogger(ProcedureJob.class);

    private JdbcTemplate jdbcTemplate;
    private String scriptPath;
    private ArgumentService argumentService;
    private JobKeyStatusService jobKeyStatusService;
    private String jobName;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String jobParameters = getJobParameters();
        try {
            logger.info("开始执行时间是：{}, 存储过程是：{}, 参数是：{}", DateTime.getCurrentDateTime(), scriptPath, jobParameters);
            jdbcTemplate.execute("call " + scriptPath + jobParameters);
            jobKeyStatusService.setJobCompleted(jobName);
            logger.info("存储过程执行完成，存储过程名称是：{}", scriptPath);
        } catch (Exception e) {
            jobKeyStatusService.setJobError(jobName);
            e.printStackTrace();
            logger.error("存储过程执行错误，存储过程是：{}, 参数是：{}, 错误消息是：{]", scriptPath, jobParameters, e.getMessage());
        }
    }

    private String getJobParameters() {
        String jobId = JoinCode.getTaskCode(jobName);
        List<TaskArgumentEntity> list = argumentService.queryArgument(jobId);
        if (list == null) {
            return "()";
        }
        StringBuilder params = new StringBuilder("(");
        for (TaskArgumentEntity ele : list) {
            params.append("'").append(ele.getArgValue()).append("',");
        }
        if (params.length() > 1) {
            params.setCharAt(params.length() - 1, ')');
        } else {
            params.append(')');
        }
        return params.toString();
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    public void setArgumentService(ArgumentService argumentService) {
        this.argumentService = argumentService;
    }

    public void setJobKeyStatusService(JobKeyStatusService jobKeyStatusService) {
        this.jobKeyStatusService = jobKeyStatusService;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
