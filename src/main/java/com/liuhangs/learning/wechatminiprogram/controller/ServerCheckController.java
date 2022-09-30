package com.liuhangs.learning.wechatminiprogram.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liuhangs.learning.wechatminiprogram.common.Result;

import lombok.RequiredArgsConstructor;

/**
 * @author hang.liu6
 * @version 1.0
 * @date 2022/9/30 11:33
 */
@RequestMapping("/pa/check")
@RestController
@RequiredArgsConstructor
public class ServerCheckController {

    @PostMapping("/server")
    public void checkServer(String signature, String timestamp, String nonce, String echostr, HttpServletResponse response) {
        String session = userService.createSession(authCode);
        return Result.success(session);
    }
}
