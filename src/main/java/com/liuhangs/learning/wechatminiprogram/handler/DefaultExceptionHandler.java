package com.liuhangs.learning.wechatminiprogram.handler;

import com.liuhangs.learning.wechatminiprogram.common.ErrorCode;
import com.liuhangs.learning.wechatminiprogram.common.Result;
import com.liuhangs.learning.wechatminiprogram.exception.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 控制器增强处理(返回 JSON 格式数据)，添加了这个注解的类能被 classpath 扫描自动发现
public class DefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class) // 捕获 Controller 中抛出的指定类型的异常，也可以指定其他异常
    public Result<Void> handler(Exception exception){

        if (exception instanceof BusinessException){
            BusinessException customException = (BusinessException) exception;
            return Result.error(customException.getCode(), customException.getMsg());
        } else {
            return Result.error(ErrorCode.OTHER_ERROR);
        }
    }
}