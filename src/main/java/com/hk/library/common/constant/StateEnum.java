package com.hk.library.common.constant;

public enum StateEnum {

    DISABLE("0"),
    ENABLE("1"),
    BORROWING("2"),
    BORROWED("3"),

    ;

    private String state;
    private int stateCode;

    StateEnum(String state) {
        this.state = state;
        this.stateCode = Integer.valueOf(state);
    }

    public static StateEnum getValue(String state) {
        for (StateEnum stateEnum : StateEnum.values()) {
            if (stateEnum.getState().equals(state)) {
                return stateEnum;
            }
        }

        return null;
    }

    public String getState() {
        return state;
    }

    public int getStateCode() {
        return stateCode;
    }

    public String toString() {
        return super.toString() + "(" + state + ")";
    }
}
