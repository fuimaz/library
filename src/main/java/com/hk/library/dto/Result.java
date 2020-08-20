package com.hk.library.dto;


import com.hk.library.common.constant.ReturnCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String msg;
    private T data;


    public Result(ReturnCodeEnum returnCodeEnum) {
        this.code = ReturnCodeEnum.SUCCESS.getCode();
        this.msg = returnCodeEnum.getMsg();
    }

    public static <T> Result success(T t) {
        Result result = new Result(ReturnCodeEnum.SUCCESS);

        result.setData(t);
        return result;
    }

    public static Result result(boolean r) {
        Result<Boolean> result = new Result(ReturnCodeEnum.SUCCESS);
        if (r) {
            result.setCode(ReturnCodeEnum.SUCCESS.getCode());
            result.setMsg(ReturnCodeEnum.SUCCESS.getMsg());
        } else {
            result.setCode(ReturnCodeEnum.FAILED.getCode());
            result.setMsg(ReturnCodeEnum.FAILED.getMsg());
        }

        result.setData(r);
        return result;
    }

    public static <T> Result error(int code, String msg) {
        Result<T> result = new Result<>(code, msg, null);
        return result;
    }

    public static <T> Result error(ReturnCodeEnum returnCodeEnum, String msg) {
        Result<T> result = new Result<>(returnCodeEnum.getCode(), msg, null);
        return result;
    }

    public static <T> Result error(ReturnCodeEnum returnCodeEnum) {
        Result<T> result = new Result<>(returnCodeEnum.getCode(), returnCodeEnum.getMsg(), null);
        return result;
    }
}
