package com.liuhangs.learning.wechatminiprogram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liuhangs.learning.wechatminiprogram.service.ServerCheckService;

import lombok.RequiredArgsConstructor;

/**
 * @author hang.liu6
 * @version 1.0
 * @date 2022/9/30 11:33
 */
@RequestMapping("/pa/check/token")
@RestController
@RequiredArgsConstructor
public class ServerCheckController {

    private final ServerCheckService serverCheckService;

    @GetMapping(produces = "text/html;charset=utf-8")
    public String checkServer(@RequestParam(name = "signature", required = false) String signature,
        @RequestParam(name = "timestamp", required = false) String timestamp,
        @RequestParam(name = "nonce", required = false) String nonce,
        @RequestParam(name = "echostr", required = false) String echostr) {
        return serverCheckService.checkServer(signature, timestamp, nonce, echostr);
    }

    @PostMapping(produces = "text/html;charset=utf-8")
    public String replyUserMsg(@RequestBody String requestBody,
        @RequestParam("signature") String signature,
        @RequestParam("timestamp") String timestamp,
        @RequestParam("nonce") String nonce,
        @RequestParam(name = "encrypt_type", required = false) String encType,
        @RequestParam(name = "msg_signature", required = false) String msgSignature) {
        return serverCheckService.replyUserMsg(requestBody, signature, timestamp, nonce, encType, msgSignature);
    }
}
