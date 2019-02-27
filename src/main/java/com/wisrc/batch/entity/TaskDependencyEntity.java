package com.wisrc.batch.entity;

import lombok.Data;

/**
 * Created by hzwy23 on 2017/5/27.
 */
@Data
public class TaskDependencyEntity {
    private String uuid;
    private String jobKey;
    private String upJobKey;
    private String domainId;
}
