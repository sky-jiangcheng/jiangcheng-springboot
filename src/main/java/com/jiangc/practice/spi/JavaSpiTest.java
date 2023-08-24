package com.jiangc.practice.spi;

import java.util.ServiceLoader;

import com.jiangc.practice.spi.api.IShout;


public class JavaSpiTest {

	public static void main(String[] args) {
		System.out.println("------------->start");
		
		ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
		
		for (IShout iShout : shouts) {
			iShout.shout();
		}
		
		System.out.println("------------->end");
		
	}
}
