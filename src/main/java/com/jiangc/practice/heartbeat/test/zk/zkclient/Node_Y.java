package com.jiangc.practice.heartbeat.test.zk.zkclient;


import org.apache.curator.framework.CuratorFramework;
public class Node_Y {
    public static void main(String[] args) throws Exception {
        setNode1();
        setNode2();
        setNode3();
    }
    public static void setNode1() throws Exception {
        CuratorFramework connection = CuraterUnil.createConnection();
        connection.setData()
                .forPath("/create1", "setdata".getBytes());
    }
    public static void setNode2() throws Exception {
        CuratorFramework connection = CuraterUnil.createConnection();
        connection.setData()
                //版本号
                .withVersion(-1)
                .forPath("/create4", "setdata".getBytes());
    }
    public static void setNode3() throws Exception {
        CuratorFramework connection = CuraterUnil.createConnection();
        connection.setData()
                .withVersion(-1)
                .inBackground((curatorFramework, curatorEvent) ->
                        System.out.println(curatorEvent.getType())
                )
                .forPath("/create1", "setdata1".getBytes());
        Thread.sleep(1000);
    }
}