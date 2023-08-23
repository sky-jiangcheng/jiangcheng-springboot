package com.jiangc.practice.zk.zkclient;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;

public class CuraterUnil {
    static CuratorFramework build=null;
    public static CuratorFramework createConnection() {
        build = CuratorFrameworkFactory.builder()
//                .connectString("192.168.88.3:2181,192.168.88.4:2181,192.168.88.5:2181")
        		.connectString("10.70.5.11:2181,10.70.5.12:2181,10.70.5.13:2181")
                .sessionTimeoutMs(4000)
                //重连机制
                .retryPolicy(new RetryOneTime(1000))
                .namespace("create.curator")
                .build();
        build.start();
        System.out.println(build.isStarted() ? "创建成功" : "创建失败");
        return build;
    }
    public static void closeConnection() {
        build.close();
    }
}