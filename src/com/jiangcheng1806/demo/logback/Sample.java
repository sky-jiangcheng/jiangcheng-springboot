package com.jiangcheng1806.demo.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;


public class Sample {
	private static final Logger LOGGER = LoggerFactory.getLogger(Sample.class);
public static void main(String[] args) {
	LOGGER.info(">>>>>start a loback test");
	/*
	 * Logback 初始化时，根据以下顺序尝试配置：
	 * 
	 * 类路径下尝试寻找 logback-test.xml 若没有，类路径下尝试寻找 logback.groovy 若没有，类路径下尝试寻找
	 * logback.xml 若没有，尝试基于 Java SPI 机制寻找 com.qos.logback.classic.spi.Configurator
	 * 接口的实现 若以上都没有，Logback 会使用最基本的 BasicConfigurator 配置自己。 这将使用 TTLLLayout（类似
	 * PatternLayout）
	 * 以"%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"模式格式化日志，并将
	 * ConsoleAppender 附加到 root Logger，这会输出到控制台，且 root 被指定为 DEBUG 等级。
	 */
	StatusPrinter.print((LoggerContext) LoggerFactory.getILoggerFactory());
}
}
