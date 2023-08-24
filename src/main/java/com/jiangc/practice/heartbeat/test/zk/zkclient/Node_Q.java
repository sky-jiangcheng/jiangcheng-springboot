package com.jiangc.practice.heartbeat.test.zk.zkclient;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;

public class Node_Q {
    public static void main(String[] args) throws Exception {
        getData1();
        getData2();
        getData3();
    }
    public  static  void  getData1() throws Exception {
        CuratorFramework connection = CuraterUnil.createConnection();
        byte[] bytes = connection.getData()
                .forPath("/create1");
        System.out.println(new String(bytes));
    }
    public  static  void  getData2() throws Exception {
        CuratorFramework connection = CuraterUnil.createConnection();
        //读取字节属性
        Stat stat = new Stat();
        byte[] bytes = connection.getData()
                //读取属性
                .storingStatIn(stat)
                .forPath("/create1");
        System.out.println(new String(bytes));
        System.out.println(stat.getVersion());
    }
    public  static  void  getData3() throws Exception {
        CuratorFramework connection = CuraterUnil.createConnection();
        connection.getData()
                .inBackground(((curatorFramework, curatorEvent) ->
                {
                    System.out.println(curatorEvent.getPath());
                    System.out.println(curatorEvent.getType());
                    System.out.println(new String(curatorEvent.getData()));
                }))
                .forPath("/create1");
        Thread.sleep(1000);
        System.out.println("====");
    }
}