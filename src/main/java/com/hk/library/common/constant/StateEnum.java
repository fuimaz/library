package com.hk.library.common.constant;

public enum StateEnum {

    DISABLE("0"),
    ENABLE("1"),
    CANCEL("2"),
    AUDITING("3"),
    BOOKED("4"),
    WAITING("5"),
    PROCESSING("6"),

    ;

    private String state;
    private int stateCode;

    StateEnum(String state) {
        this.state = state;
        this.stateCode = Integer.valueOf(state);
    }

    public String getState() {
        return state;
    }

    public int getStateCode() {
        return stateCode;
    }

    public static StateEnum getValue(String state) {
        for (StateEnum stateEnum : StateEnum.values()) {
            if (stateEnum.getState().equals(state)) {
                return stateEnum;
            }
        }

        return null;
    }

    public String toString() {
        return super.toString() + "(" + state + ")";
    }
}
