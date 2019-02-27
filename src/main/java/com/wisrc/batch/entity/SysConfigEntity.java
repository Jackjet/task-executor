package com.wisrc.batch.entity;

import lombok.Data;

/**
 * Created by hzwy23 on 2017/6/15.
 */
@Data
public class SysConfigEntity {
    private String configId;
    private String configDesc;
    private String configValue;
    private String image;
    private String details;
}
