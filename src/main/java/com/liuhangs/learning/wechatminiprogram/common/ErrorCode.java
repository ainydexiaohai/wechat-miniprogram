package com.liuhangs.learning.wechatminiprogram.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SUCCESS("10000", "成功"),

    NOT_BIND("10001", "用户未绑定"),
    AUTH_CODE_INVILID("10002", "微信鉴权码失效"),

    OTHER_ERROR("99999", "未知错误"),
    ;


    private String code;

    private String desc;
}
