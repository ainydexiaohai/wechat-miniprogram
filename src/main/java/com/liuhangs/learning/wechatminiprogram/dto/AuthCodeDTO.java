package com.liuhangs.learning.wechatminiprogram.dto;

import lombok.Data;

@Data
public class AuthCodeDTO {

    private String openid;

    private String session_key;

    private String unionid;

    private String errcode;

    private String errmsg;
}
