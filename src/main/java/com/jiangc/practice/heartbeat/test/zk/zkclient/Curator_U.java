package com.jiangc.practice.heartbeat.test.zk.zkclient;

import org.apache.curator.framework.CuratorFramework;

public class Curator_U {
    public static void main(String[] args) throws Exception {
       // deleteNode();
        deleteNode1();
        deleteNode2();
        deleteNode3();
    }
    //删除节点
    public static void deleteNode() throws Exception {
        CuratorFramework connection = CuraterUnil.createConnection();
        connection.delete()
                .forPath("/create1");
    }
    //删除节点 版本
    public static void deleteNode1() throws Exception {
        CuratorFramework connection = CuraterUnil.createConnection();
        connection.delete()
                .withVersion(-1)
                .forPath("/create");
    }
    //删除有子节点的节点
    public static void deleteNode2() throws Exception {
        CuratorFramework connection = CuraterUnil.createConnection();
        connection.delete()
                .deletingChildrenIfNeeded()
                .withVersion(-1)
                .forPath("/createfirst");
    }
    //异步删除节点
    public static void deleteNode3() throws Exception {
        CuratorFramework connection = CuraterUnil.createConnection();
        connection.delete()
                .withVersion(-1)
                .inBackground((curatorFramework, curatorEvent) -> System.out.println(curatorEvent.getType()))
                .forPath("/create4");
    }
}