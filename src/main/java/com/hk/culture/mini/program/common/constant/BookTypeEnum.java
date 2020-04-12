package com.hk.culture.mini.program.common.constant;

public enum BookTypeEnum {
    ACTIVITY("activity"),
    VENUES("venues"),
    ;

    private String msg;


    BookTypeEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
