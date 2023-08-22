package com.jiangc.practice.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class LoggerFactoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerFactoryTest.class);
	public static void main(String[] args) {
		LOGGER.info(">>>>>start a loback test");
		StatusPrinter.print((LoggerContext) LoggerFactory.getILoggerFactory());
	}
}
