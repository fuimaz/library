package com.hk.culture.mini.program.common.constant;

public enum ReturnCodeEnum {
    SUCCESS(1001, "success"),
    PARAM_ERROR(1002, "param missing"),
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
