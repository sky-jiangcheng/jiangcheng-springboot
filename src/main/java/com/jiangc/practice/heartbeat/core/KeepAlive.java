package com.jiangc.practice.heartbeat.core;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KeepAlive implements Serializable {


    private static final long serialVersionUID = -4558958166553742730L;

    @Override
    public String toString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t维持连接�?";
    }
}
