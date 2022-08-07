package com.liuhangs.learning.wechatminiprogram.service;

import com.liuhangs.learning.wechatminiprogram.common.Constants;
import com.liuhangs.learning.wechatminiprogram.common.ErrorCode;
import com.liuhangs.learning.wechatminiprogram.dto.AuthCodeDTO;
import com.liuhangs.learning.wechatminiprogram.exception.BusinessException;
import com.liuhangs.learning.wechatminiprogram.utils.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private static final String AUTH_CODE_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Value("${miniprogram.app.id}")
    private String APP_ID;

    @Value("${miniprogram.app.secret}")
    private String APP_SECRET;

    private final RedisTemplate<String, Object> redisTemplate;

    public String createSession(String authCode) {
        Map<String, String> params = new HashMap<>();
        params.put("appid", APP_ID);
        params.put("secret", APP_SECRET);
        params.put("js_code", authCode);
        params.put("grant_type", "authorization_code");
        AuthCodeDTO authCodeDTO = HttpUtils.doGet(AUTH_CODE_URL, params, AuthCodeDTO.class);
        if(Objects.isNull(authCodeDTO) || !(Objects.equals(authCodeDTO.getErrcode(), "0") || Objects.isNull(authCodeDTO.getErrcode()))) {
            log.error("用户鉴权码失效， authCode={}， authCodeDTO={}", authCode, authCodeDTO);
            throw new BusinessException(ErrorCode.AUTH_CODE_INVILID);
        }

        // 生成业务session
        String bizSession = StringUtils.remove(UUID.randomUUID().toString(), "-");
        redisTemplate.opsForValue().set(Constants.OPEN_ID_KEY + bizSession, authCodeDTO.getOpenid(), 30, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(Constants.SESSION_KEY + bizSession, authCodeDTO.getSession_key(), 30, TimeUnit.MINUTES);
        return bizSession;
    }
}
