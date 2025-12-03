package com.bite.springblogdemo;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    void genToken() {
        Key key = Keys.hmacShaKeyFor("MUlZiBZ7YTUqLO0leNtPrUdBtEOcgTiU5aZIYZX9veY=".
                getBytes(StandardCharsets.UTF_8));
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 12);
        claims.put("name", "zhangsan");
        String compact = Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
        System.out.println(compact);

        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        System.out.println(build.parse(compact).getBody());
    }

    @Test
    void genkey() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        System.out.println(Encoders.BASE64.encode(key.getEncoded()));
    }
}
