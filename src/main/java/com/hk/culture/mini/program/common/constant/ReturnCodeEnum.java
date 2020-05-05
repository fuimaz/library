package com.hk.culture.mini.program.common.constant;

public enum ReturnCodeEnum {
    SUCCESS(1001, "success"),
    FAILED(1002, "服务器异常"),
    PARAM_ERROR(1003, "param missing"),
    STATE_ERROR(1004, "state error"),
    OVER_LIMIT(1005, "times over limit"),
    RECORD_EXISTS(1006, "record exists, don't repeat submit"),
    RECORD_NOT_EXISTS(1007, "record not exists"),
    ;

    private String msg;
    private int code;


    ReturnCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
