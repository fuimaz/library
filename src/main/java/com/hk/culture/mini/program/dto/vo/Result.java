package com.hk.culture.mini.program.dto.vo;


import com.hk.culture.mini.program.common.constant.ReturnCodeEnum;
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
        this.code = returnCodeEnum.SUCCESS.getCode();
        this.msg = returnCodeEnum.getMsg();
    }

    public static <T> Result success(T t){
        Result result = new Result(ReturnCodeEnum.SUCCESS);

        result.setData(t);
        return result;
    }

    public static <T> Result error(int code, String msg){
        Result<T> result = new Result<>(code, msg, null);
        return result;
    }

    public static <T> Result error(ReturnCodeEnum returnCodeEnum, String msg){
        Result<T> result = new Result<>(returnCodeEnum.getCode(), msg, null);
        return result;
    }

    public static <T> Result error(ReturnCodeEnum returnCodeEnum){
        Result<T> result = new Result<>(returnCodeEnum.getCode(), returnCodeEnum.getMsg(), null);
        return result;
    }
}
