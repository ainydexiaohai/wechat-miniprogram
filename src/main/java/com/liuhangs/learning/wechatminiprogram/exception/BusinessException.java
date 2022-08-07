package com.liuhangs.learning.wechatminiprogram.exception;

import com.liuhangs.learning.wechatminiprogram.common.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final String code;

    private final String msg;

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDesc());
        this.code = errorCode.getCode();
        this.msg = errorCode.getDesc();
    }
}
