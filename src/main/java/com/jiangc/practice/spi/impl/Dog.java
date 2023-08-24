package com.jiangc.practice.spi.impl;

import com.jiangc.practice.spi.api.IShout;

public class Dog implements IShout{

	@Override
	public void shout() {
		// TODO Auto-generated method stub
		System.out.println("wang wang");
	}

}
