package com.wisrc.batch.dao;

import com.wisrc.batch.dto.BatchRunConfDto;

import java.util.Map;

/**
 * Created by hzwy23 on 2017/6/14.
 */
public interface BatchJobStatusDao {
    int init(BatchRunConfDto confDto, Map<String, Integer> map);

    int setJobRunning(BatchRunConfDto conf, String jobId, int status);

    int setJobEnd(BatchRunConfDto conf, String jobId, int status);
}
