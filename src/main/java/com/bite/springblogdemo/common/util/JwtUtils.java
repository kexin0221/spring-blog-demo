package com.bite.springblogdemo.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;

@Slf4j
public class JwtUtils {
    private static final String SECRETED_STRING = "MUlZiBZ7YTUqLO0leNtPrUdBtEOcgTiU5aZIYZX9veY=";

    private static final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRETED_STRING));

    public static String genToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).signWith(key).compact();
    }

    public static Claims parseToken(String token) {
        if (!StringUtils.hasLength(token)) {
            return null;
        }
        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        Claims claims = null;
        try {
            claims = build.parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error("token解析失败, token: {}", token);
        }
        return claims;
    }
}
