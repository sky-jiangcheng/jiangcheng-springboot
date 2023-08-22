package com.jiangc.practice.zk.zkclient;

import org.apache.curator.framework.CuratorFramework;

import java.util.List;

public class Curater_P {
    public static void main(String[] args) throws Exception {
         getchild1();
         getchild2();
    }
    public  static  void  getchild1() throws Exception {
        CuratorFramework connection = CuraterUnil.createConnection();
        List<String> list = connection.getChildren()
                .forPath("/createfirst");
        for (String s : list) {
            System.out.println(s);
        }
    }
    public  static  void  getchild2() throws Exception {
        CuratorFramework connection = CuraterUnil.createConnection();
        connection.getChildren()
                .inBackground(((curatorFramework, curatorEvent) -> {
                    System.out.println(curatorEvent.getType());
                    System.out.println(curatorEvent.getPath());
                    List<String> children = curatorEvent.getChildren();
                    children.forEach(System.out::println);
                }))
                .forPath("/createfirst");
        Thread.sleep(1000);
    }
}