package com.checkTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = { "classpath:properties/config.properties" })
@Component
public class CheckTime {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CheckTime.class);

	private @Value("${checkTime}") String queryEventUrl;

	// @Scheduled(cron = "${checkTime}")
	// 间隔5秒执行
	public void taskCycle() {
		LOGGER.info("使用SpringMVC框架配置定时任务  + springMVC读取配置文件信息:" + queryEventUrl);
	}

}
