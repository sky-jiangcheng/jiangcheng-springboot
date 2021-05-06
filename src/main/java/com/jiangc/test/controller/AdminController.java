package com.jiangc.test.controller;

import com.jiangc.test.component.OnlineCounter;
import com.jiangc.test.response.RespResult;
import com.jiangc.test.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: AdminController
 * @Description:
 * @Author: jiangcheng31
 * @Date: 2021/5/6 17:34
 */
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private OnlineCounter onlineCounter;

    @PostMapping(value = "/login")
    public RespResult login(@RequestParam("password") String password,
                            @RequestParam("username") String username,
                            HttpSession session) throws Exception{
        boolean isRight = true;//adminService.checkUsernameAndPwd(username, password);
        if (isRight) {
            //获取新token，过期时间为12h
            String token = adminService.getToken(username);
            //OnlineCount.getInstance().insertToken(token);

            Map map = new HashMap<String, Integer>();
            map.put("username", username);
            map.put("token", token);
            return RespResult.ok(map);
        }
        return RespResult.error("用户名或密码错误，请重新输入");
    }

    @GetMapping(value = "/getOnlineCount")
    public int getRealOnlineCount() {
        Integer onlines = onlineCounter.getOnlineCount();
        return onlines;
    }
}
