package com.jiangc.test;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.Test;

import com.jiangc.practice.util.JWTUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * @ClassName: JwtUtilTest
 * @Description:
 * @Author: jiangcheng31
 * @Date: 2021/5/6 15:19
 */
public class JWTUtilsTest {

    @Test
    public void createJWT() throws Exception {
        System.out.println(">>>>>>test start<<<<<<<<<");
        Map claims = new HashMap();
        claims.put("aa","test");

        String subject = "test";

        long ttl = 1000L;

        String token = JWTUtils.createJWT(claims,subject,ttl);

        assertNotNull(token,"token不能为空");

        System.out.println("token为"+token);
    }

    @Test
    public void parseJWT() throws Exception {
        System.out.println(">>>>>>test start<<<<<<<<<");

        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJhYSI6InRlc3QiLCJzdWIiOiJ0ZXN0IiwiZXhwIjoxNjIwMjg2MTExLCJpYXQiOjE2MjAyODYxMTB9.9LC3wOC4TJ5nHwknmf2aDwRLOYsSjczYkkIZYAX66l0";

        Claims claims = null;
        try {
            claims = JWTUtils.parseJWT(jwt);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            System.out.println("token已过期");
        }

        System.out.println("claims为"+claims);
    }
}