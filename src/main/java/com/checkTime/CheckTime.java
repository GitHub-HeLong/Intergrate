package com.checkTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@PropertySource(value = { "classpath:properties/config.properties" })
@Component
public class CheckTime {

	private @Value("${checkTime}") String queryEventUrl;

	@Scheduled(cron = "${checkTime}")
	// 间隔5秒执行
	public void taskCycle() {
		System.out.println("使用SpringMVC框架配置定时任务  + springMVC读取配置文件信息:"
				+ queryEventUrl);
	}

}
