package com.bite.springblogdemo.common.util;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class SecurityUtils {
    /**
     * 加密 md5(salt+password)
     * @param password 明文
     * @return salt+md5(salt+password)
     */
    public static String encrypt(String password) {
        String salt = UUID.randomUUID().toString().replace("-", "");
        String securityPassword = DigestUtils.md5DigestAsHex((salt+password).
                getBytes(StandardCharsets.UTF_8));
        return salt + securityPassword;
    }

    /**
     * 验证
     * @return
     */
    public static Boolean verify(String inputPassword, String sqlPassword) {
        if (!StringUtils.hasLength(inputPassword)) {
            return false;
        }
        if (sqlPassword == null || sqlPassword.length() != 64) {
            return false;
        }
        String salt = sqlPassword.substring(0, 32);
        String securityPassword = DigestUtils.md5DigestAsHex((salt+inputPassword).
                getBytes(StandardCharsets.UTF_8));
        return sqlPassword.equals(salt + securityPassword);
    }
}
