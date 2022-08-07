package com.liuhangs.learning.wechatminiprogram.controller;

import com.liuhangs.learning.wechatminiprogram.common.Result;
import com.liuhangs.learning.wechatminiprogram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create/session")
    public Result<String> createSess8ion(String authCode) {
        String session = userService.createSession(authCode);
        return Result.success(session);
    }
}
