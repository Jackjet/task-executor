package com.wisrc.batch.dao;

import com.wisrc.batch.dto.BatchRunConfDto;

import java.util.Map;

/**
 * Created by hzwy23 on 2017/6/15.
 */
public interface BatchGroupStatusDao {
    int init(BatchRunConfDto conf, Map<String, Integer> map);

    int setGroupRunning(BatchRunConfDto conf, String suiteKey, int status);

    int setGroupEnd(BatchRunConfDto conf, String suiteKey, int status);
}
