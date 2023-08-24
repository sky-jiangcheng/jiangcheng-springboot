package com.jiangc.practice.heartbeat;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class MyServer {
    public interface ObjectAction{
        Object doAction(Object rec,MyServer server);
    }

    public static final class DefaultObjectAction implements ObjectAction{

        @Override
        public Object doAction(Object rec, MyServer server) {
            System.out.println("处理并返回："+rec);
            return rec;
        }
    }

    private int port;
    private volatile boolean running = false;
    private long receiveTimeDelay = 3000;

    private ConcurrentHashMap<Class,ObjectAction> actionMapping = new ConcurrentHashMap<>();
    private Thread connWatchDog;

    public MyServer(int port){
        this.port = port;
    }

    public void start(){
        if (running){
            return;
        }
        ;
        running = true;
        connWatchDog = new Thread(new ConnWatchDog());
        connWatchDog.start();
    }

    public void stop(){
        if (running){
            running = false;
        }

        if (connWatchDog != null){
            connWatchDog.stop();
        }
    }

    class ConnWatchDog implements Runnable {

        @Override
        public void run() {
            try {
                ServerSocket socket = new ServerSocket(port,5);
                while (running){
                    Socket s = socket.accept();
                    new Thread(new SocketAction(s)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
                MyServer.this.stop();
            }
        }
    }

    class SocketAction implements Runnable {

        Socket s;
        boolean run = true;
        long lastReceiveTime = System.currentTimeMillis();

        public SocketAction(Socket s){
            this.s = s;
        }

        @Override
        public void run(){

            while (running && run){
                if (System.currentTimeMillis() - lastReceiveTime > receiveTimeDelay){
                    overThis();
                } else {
                    try {
                        InputStream in = s.getInputStream();
                        if (in.available()>0){
                            ObjectInputStream ois = new ObjectInputStream(in);
                            Object obj = ois.readObject();
                            lastReceiveTime = System.currentTimeMillis();
                            System.out.println("接收�?/t"+obj);
                            ObjectAction oa = actionMapping.get(obj.getClass());
                            oa = oa == null ? new DefaultObjectAction() : oa;
                            Object out = oa.doAction(obj,MyServer.this);
                            if (out !=  null){
                                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                                oos.writeObject(out);
                                oos.flush();
                            } else {
                                Thread.sleep(10);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        overThis();
                    }
                }
            }
        }

        private void overThis(){
            if (run){
                run = false;
            }
            ;
            if (s != null){
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("关闭：\t"+s.getRemoteSocketAddress());
        }
    }

    public static void main(String[] args) {
        int port = 65432;
        MyServer server = new MyServer(port);
        server.start();
    }
}
