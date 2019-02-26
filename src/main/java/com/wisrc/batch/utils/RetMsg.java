package com.wisrc.batch.utils;

/**
 * Created by hzwy23 on 2017/6/26.
 */
public class RetMsg {
    private Integer code;
    private String message;
    private Object details;

    public RetMsg() {
    }

    public RetMsg(Integer code, String message, Object details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public static RetMsg success() {
        return new RetMsg(200, "Success", null);
    }

    public static RetMsg success(Object data) {
        return new RetMsg(200, "Success", data);
    }

    public Boolean checkCode() {
        if (SysStatus.SUCCESS_CODE == code) {
            return true;
        }
        return false;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "RetMsg{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", details=" + details +
                '}';
    }
}
