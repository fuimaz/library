package com.hk.culture.mini.program.common.constant;

public enum StateEnum {

    DISABLE("0"),
    ENABLE("1"),
    CANCEL("2"),
    AUDITING("3"),
    Booked("4"),
    ;

    private String state;

    StateEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
