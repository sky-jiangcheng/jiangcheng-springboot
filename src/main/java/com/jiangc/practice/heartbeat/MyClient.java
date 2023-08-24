package com.jiangc.practice.heartbeat;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class MyClient {

    public static interface ObjectAction {
        void doAction(Object obj,MyClient client);
    }

    public static final class DefaultObjectAction implements ObjectAction {

        @Override
        public void doAction(Object obj, MyClient client) {
            System.out.println("处理：\t"+obj.toString());
        }
    }

    private String serverIp;
    private int port;
    private Socket socket;

    private boolean running = false;

    private long lastSendTime;

    private ConcurrentHashMap<Class,ObjectAction> actionMapping = new ConcurrentHashMap<>();

    private MyClient(String serverIp,int port){
        this.serverIp = serverIp;
        this.port = port;
    }

    public void start() throws IOException {
        if (running) {
            return;
        }
        socket = new Socket(serverIp,port);
        System.out.println("本地端口�?" + socket.getLocalPort());
        lastSendTime = System.currentTimeMillis();
        running = true;

        //keep long connection,heartbeat every 2 seconds
        new Thread(new KeepAliveWatchDog()).start();
        //handle msg
        new Thread(new ReceiveWatchDog()).start();

    }

    public void stop(){
        if (running){
            running = false;
        };
    }

    public void addActionMap(Class<Object> cls,ObjectAction action){
        actionMapping.put(cls,action);
    }

    public void sendObject(Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(obj);
        System.out.println("发�?�：\t"+obj);
        oos.flush();
    }

    class KeepAliveWatchDog implements Runnable {
        long checkDelay = 10;
        long keepAliveDelay = 2000;

        @Override
        public void run() {
            while(running){
                if (System.currentTimeMillis() - lastSendTime > keepAliveDelay){
                    try {
                        MyClient.this.sendObject(new KeepAlive());
                    } catch (IOException e) {
                        e.printStackTrace();
                        MyClient.this.stop();
                    }
                    lastSendTime = System.currentTimeMillis();
                } else {
                    try {
                        Thread.sleep(checkDelay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        MyClient.this.stop();
                    }
                }
            }
        }
    }

    class ReceiveWatchDog  implements Runnable{

        @Override
        public void run() {
            while (running){
                try{
                    InputStream in = socket.getInputStream();
                    if (in.available() > 0){
                        ObjectInputStream ois = new ObjectInputStream(in);
                        Object obj = ois.readObject();
                        System.out.println("接收：\t" + obj);
                        ObjectAction oa = actionMapping.get(obj.getClass());
                        oa = oa == null ? new DefaultObjectAction() : oa;
                        oa.doAction(obj,MyClient.this);
                    } else {
                        Thread.sleep(10);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MyClient.this.stop();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String serverIp = "127.0.0.1";
        int port = 65432;
        MyClient client = new MyClient(serverIp,port);
        client.start();
    }
}
