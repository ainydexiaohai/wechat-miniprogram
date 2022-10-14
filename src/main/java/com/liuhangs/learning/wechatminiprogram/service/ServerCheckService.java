package com.liuhangs.learning.wechatminiprogram.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author hang.liu6
 * @version 1.0
 * @date 2022/9/30 11:34
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ServerCheckService {

    private final WxMpService mpService;

    private final WxMpMessageRouter messageRouter;

    public String checkServer(String signature, String timestamp, String nonce, String echostr) {
        log.info("接收到来自微信服务器的认证消息：signature = [{}], timestamp = [{}], nonce = [{}], echostr = [{}]",
            signature, timestamp, nonce, echostr);

        if (!mpService.checkSignature(timestamp, nonce, signature)) {
            log.error("不是来自微信公众号的消息");
        }

        // 解密微信消息
        //String msg = new WxMpCryptUtil(mpService.getWxMpConfigStorage()).decrypt(echostr);
        log.info("解密微信消息为：echostr={}", echostr);
        return echostr;
    }

    public String replyUserMsg(String requestBody, String signature, String timestamp,
        String nonce, String encType, String msgSignature) {
        log.info("接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
                + " timestamp=[{}], nonce=[{}], requestBody=[{}] ",
            signature, encType, msgSignature, timestamp, nonce, requestBody);

        if (!mpService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        String out = null;
        if (encType == null) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if (outMessage == null) {
                return "";
            }

            out = outMessage.toXml();
        } else if ("aes".equalsIgnoreCase(encType)) {
            // aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody, mpService.getWxMpConfigStorage(),
                timestamp, nonce, msgSignature);
            log.debug("消息解密后内容为：{} ", inMessage.toString());
            WxMpXmlOutMessage outMessage = this.route(inMessage);
            if (outMessage == null) {
                return "";
            }

            out = outMessage.toEncryptedXml(mpService.getWxMpConfigStorage());
        }

        log.debug("组装回复信息：{}", out);
        return out;
    }

    private WxMpXmlOutMessage route(WxMpXmlMessage message) {
        try {
            return this.messageRouter.route(message);
        } catch (Exception e) {
            log.error("路由消息时出现异常！", e);
        }
        return null;
    }
}
