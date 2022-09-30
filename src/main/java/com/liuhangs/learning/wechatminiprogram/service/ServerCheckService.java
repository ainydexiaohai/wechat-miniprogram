package com.liuhangs.learning.wechatminiprogram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * @author hang.liu6
 * @version 1.0
 * @date 2022/9/30 11:34
 */
@Slf4j
@Service
public class ServerCheckService {

    @Autowired
    private WxMpService mpService;

    public String checkServer(String signature, String timestamp, String nonce, String echostr) {
        log.info("接收到来自微信服务器的认证消息：signature = [{}], timestamp = [{}], nonce = [{}], echostr = [{}]",
            signature, timestamp, nonce, echostr);

        if(!mpService.checkSignature(timestamp, nonce, signature)) {
            log.error("不是来自微信公众号的消息");
        }



    }
}
