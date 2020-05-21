package com.hk.culture.mini.program.config;

import com.hk.culture.mini.program.common.constant.ReturnCodeEnum;
import com.hk.culture.mini.program.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(HttpServletRequest request, Exception e) {
        //绑定异常是需要明确提示给用户的
//        if (e instanceof BindException) {
//            BindException exception = (BindException) e;
//            List<ObjectError> errors = exception.getAllErrors();
//            String msg = errors.get(0).getDefaultMessage();
//            return Result.error(CodeMsg.SERVER_BIND_ERROR.fillArgs(msg));
//        }

        log.error("", e);

        // 其余异常简单返回为服务器异常
        return Result.error(ReturnCodeEnum.FAILED);

    }
}