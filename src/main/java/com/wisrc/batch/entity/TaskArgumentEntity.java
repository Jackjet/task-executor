package com.wisrc.batch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hzwy23 on 2017/5/24.
 */
public class TaskArgumentEntity {
    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("task_id")
    private String task_id;

    @JsonProperty("arg_id")
    private String arg_id;

    @JsonProperty("arg_value")
    private String arg_value;

    @JsonProperty("domain_id")
    private String domain_id;

    @JsonProperty("sort_id")
    private String sort_id;

    @JsonProperty("arg_type")
    private String arg_type;

    @JsonProperty("arg_type_desc")
    private String arg_type_desc;

    @JsonProperty("code_number")
    private String code_number;

    @JsonProperty("arg_desc")
    private String arg_desc;

    @JsonProperty("fixed_arg_value")
    private String fixed_arg_value;

    public String getArgType() {
        return arg_type;
    }

    public void setArgType(String arg_type) {
        this.arg_type = arg_type;
    }

    public String getArgTypeDesc() {
        return arg_type_desc;
    }

    public void setArgTypeDesc(String arg_type_desc) {
        this.arg_type_desc = arg_type_desc;
    }

    @Override
    public String toString() {
        return "TaskArgumentEntity{" +
                "uuid='" + uuid + '\'' +
                ", task_id='" + task_id + '\'' +
                ", arg_id='" + arg_id + '\'' +
                ", arg_value='" + arg_value + '\'' +
                ", domain_id='" + domain_id + '\'' +
                ", sort_id='" + sort_id + '\'' +
                ", arg_type='" + arg_type + '\'' +
                ", arg_type_desc='" + arg_type_desc + '\'' +
                ", code_number='" + code_number + '\'' +
                ", arg_desc='" + arg_desc + '\'' +
                ", fixed_arg_value='" + fixed_arg_value + '\'' +
                '}';
    }

    public String getCodeNumber() {
        return code_number;
    }

    public void setCodeNumber(String code_number) {
        this.code_number = code_number;
    }

    public String getArgDesc() {
        return arg_desc;
    }

    public void setArgDesc(String arg_desc) {
        this.arg_desc = arg_desc;
    }

    public String getFixedArgValue() {
        return fixed_arg_value;
    }

    public void setFixedArgValue(String fixed_arg_value) {
        this.fixed_arg_value = fixed_arg_value;
    }

    public String getSortId() {
        return sort_id;
    }

    public void setSortId(String sort_id) {
        this.sort_id = sort_id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTaskId() {
        return task_id;
    }

    public void setTaskId(String task_id) {
        this.task_id = task_id;
    }

    public String getArgId() {
        return arg_id;
    }

    public void setArgId(String arg_id) {
        this.arg_id = arg_id;
    }

    public String getArgValue() {
        return arg_value;
    }

    public void setArgValue(String arg_value) {
        this.arg_value = arg_value;
    }

    public String getDomainId() {
        return domain_id;
    }

    public void setDomainId(String domain_id) {
        this.domain_id = domain_id;
    }

}
