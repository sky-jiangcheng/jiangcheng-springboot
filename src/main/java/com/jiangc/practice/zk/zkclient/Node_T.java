package com.jiangc.practice.zk.zkclient;


import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

import java.util.ArrayList;
import java.util.List;

public class Node_T {
    public static void main(String[] args) throws Exception {
     //   createNode1();
     //   createNode2();
      //  createNode3();
        createNode4();

    }
    //创建节点
    public static void createNode1() throws Exception {
        CuratorFramework connection = CuraterUnil.createConnection();
        connection.create()
                //节点类型
                .withMode(CreateMode.PERSISTENT)
                //节点权限
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                //节点路径
                //节点数据
                .forPath("/create1", "create1".getBytes());
    }
    //自定义创建....IP类型......
    public static void createNode2() throws Exception {
      List<ACL> acls = new ArrayList<>();
        Id ip = new Id("ip", "192.168.88.4");
        acls.add(new ACL(ZooDefs.Perms.ALL,ip));
        CuratorFramework connection = CuraterUnil.createConnection();
        connection.create()
                .withMode(CreateMode.PERSISTENT)
                .withACL(acls)
                .forPath("/create2","create".getBytes());
        System.out.println("==");
    }
    //递归创建   world模式
    public static void createNode3() throws Exception {
        List<ACL> acls = new ArrayList<>();
        Id id = new Id("world", "anyone");
        acls.add(new ACL(ZooDefs.Perms.ALL,id));
        CuratorFramework connection = CuraterUnil.createConnection();
        connection.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(acls)
                .forPath("/createfirst/create3","crteate3".getBytes());
        System.out.println("===");
    }
    //异步创建
    public static void createNode4() throws Exception {
        CuratorFramework connection = CuraterUnil.createConnection();
        connection.create()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                //异步
                .inBackground((curatorFramework,curatorEvent)->
                {
                    System.out.println(curatorEvent.getPath() + "=" + curatorEvent.getType());
                })
                .forPath("/create4","create4".getBytes());

    }
}