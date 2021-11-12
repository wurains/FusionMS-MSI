package com.example.component;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
@Component
public class SignatureValidation {
    private static final long MAX_REQUEST = 30 * 1000L;

    private static final long SECRET = 5003031313L;

    private Logger logger = LoggerFactory.getLogger(SignatureValidation.class);

    @Pointcut("execution(@com.example.aop.SignatureValidation * *(..))")
    private void verifyUserKey() {
    }

    @Before("verifyUserKey()")
    public void doBasicProfiling() throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = request.getHeader("token");
        String timestamp = request.getHeader("timestamp");
        try {
            Boolean check = checkToken(token, timestamp);
            if (!check) {
                // 自定义异常抛出（开发者自行换成自己的即可）
//                throw new Exception("签名验证错误");
                logger.info("校验异常");
            }
        } catch (Throwable throwable) {
            // 自定义异常抛出（开发者自行换成自己的即可）
            throw new Throwable("");
        }
    }

    private Boolean checkToken(String token, String timestamp) {
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(timestamp)) {
            return false;
        }
        long now = System.currentTimeMillis();
        long time = Long.parseLong(timestamp);
        if (now - time > MAX_REQUEST) {
            logger.info("时间戳已过期[{}][{}][{}]", now, time, (now - time));
            return false;
        }
//        String crypt = MD5Utils.getMD5(SECRET+ timestamp);

        String md5Hex = DigestUtils.md5DigestAsHex(timestamp.getBytes());

        logger.info("成功", now, time, (now - time), md5Hex);

        return true;
    }
}
