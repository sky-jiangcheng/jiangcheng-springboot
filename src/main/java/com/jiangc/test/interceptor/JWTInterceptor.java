package com.jiangc.test.interceptor;

import com.jiangc.test.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @ClassName: JWTInterceptor
 * @Description:JWT token 管理后台的拦截校验器
 * @Author: jiangcheng31
 * @Date: 2021/5/6 16:40
 */
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        // response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");

        //后台管理页面产生的token
        String token = request.getHeader("authorization");
        //判断是否过期
        Optional.ofNullable(token)
                .map(n -> {
                    try {
                        return JWTUtils.parseJWT(n);
                    } catch (Exception e) {
                        throw new RuntimeException("token不存在");
                    }
                });

        return true;
    }
}
