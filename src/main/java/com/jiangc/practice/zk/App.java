package com.jiangc.practice.zk;


import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.KeeperException;

import com.jiangc.practice.zk.zktool.CreateClient;
import com.jiangc.practice.zk.zktool.CuratorZkClientBridge;

import java.util.ArrayList;
import java.util.List;
 
/**
 * Hello world!
 */
public class App {
    public static String connectionString = "10.70.5.11:2181,10.70.5.12:2181,10.70.5.13:2181";
    public static List<String> res = new ArrayList<>();
 
    public static void main(String[] args) {
        System.out.println("Hello World!");
        CuratorFramework curatorFramework = CreateClient.createSimple(connectionString);
        curatorFramework.start();
        
        //doSomething to zookeeper
        CuratorZkClientBridge curatorZkClientBridge = new CuratorZkClientBridge(curatorFramework);
        
        //System.out.println(getNode(curatorZkClientBridge, "/"));
        
//        System.out.println(curatorZkClientBridge.getServers());
//        10.70.5.11:2181,10.70.5.12:2181,10.70.5.13:2181
        
//        System.out.println(curatorZkClientBridge.getZookeeperState());
//        CONNECTING
        
        
        curatorFramework.close();
    }
 
    public static List<String> getNode(CuratorZkClientBridge curatorZkClientBridge, String parentNode) {
        try {
            System.out.println("read parent=====>"+parentNode);
        	List<String> tmpList = curatorZkClientBridge.getChildren(parentNode, false);
            for (String tmp : tmpList) {
                String childNode = parentNode.equals("/") ? parentNode + tmp : parentNode + "/" + tmp;
                res.add(childNode);
                getNode(curatorZkClientBridge, childNode);
            }
            return res;
        } catch (KeeperException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
 
}