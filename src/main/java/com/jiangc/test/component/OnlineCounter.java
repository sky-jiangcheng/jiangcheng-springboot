package com.jiangc.test.component;

import com.jiangc.test.util.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: OnlineCounter
 * @Description:
 * @Author: jiangcheng31
 * @Date: 2021/5/6 20:09
 */
@Component
public class OnlineCounter {
    //每次打开此类只初始化一次countMap
    private static Map countMap = new ConcurrentHashMap<String,Object>();

    /**
     * 解析token并且将数据插入CountMap中
     * @param token
     */
    public void insertToken(String token){
        //获得当前时间(毫秒)
        long currentTime = System.currentTimeMillis();
        //解析token，获得签发时间
        Claims claims = null;
        try {
            claims = JWTUtils.parseJWT(token);
        } catch (Exception e) {
            throw new RuntimeException("token不存在或已过期");
        }
        Date issuedAt = claims.getIssuedAt();
        //以签发时间为key。当前时间+60s为value存入countMap中
        countMap.put(issuedAt.toString(),currentTime+60*1000);
    }

    /**
     * 获取当前在线用户数
     * @return
     */
    public Integer getOnlineCount(){
        int onlineCount = 0;
        //获取countMap的迭代器
        Iterator iterator = countMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,Object>  entry = (Map.Entry<String, Object>) iterator.next();
            Long value = (Long) entry.getValue();
            if (value > System.currentTimeMillis()) {
                //过期时间大于当前时间则没有过期
                onlineCount++;
            }

        }

        return onlineCount;
    }
}
