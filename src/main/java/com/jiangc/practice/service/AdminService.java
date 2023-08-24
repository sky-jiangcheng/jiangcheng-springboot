package com.jiangc.practice.service;

import org.springframework.stereotype.Service;

import com.jiangc.practice.util.JWTUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: AdminService
 * @Description:
 * @Author: jiangcheng31
 * @Date: 2021/5/6 20:05
 */
@Service
public class AdminService {
    public String getToken(String username) {

        //存入JWT的payload中生成token
        Map claims = new HashMap<String,Integer>();
        claims.put("admin_username",username);
        String subject = "admin";
        String token = null;
        try {
            //该token过期时间为12h
            token = JWTUtils.createJWT(claims, subject, 1000*60*60*12 );
        } catch (Exception e) {
            throw new RuntimeException("创建Token失败");
        }

        System.out.println("token:"+token);
        return token;
    }
}
