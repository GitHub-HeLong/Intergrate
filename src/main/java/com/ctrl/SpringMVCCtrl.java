package com.ctrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.es.EsDao;
import com.mq.MQSender;
import com.mq.MqTopicSendServer;
import com.server.SpringMVCService;
import com.tool.HttpTool;

@Controller
@RequestMapping("springMVCCtrl")
public class SpringMVCCtrl {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpringMVCCtrl.class);

	@Resource
	SpringMVCService springMVCService;

	@Resource
	MQSender mqSender;

	@Resource
	EsDao esDao;

	@Resource
	MqTopicSendServer mqTopicSendServer;

	@RequestMapping("springMVC")
	@ResponseBody
	public String helloSpringMVC() {
		LOGGER.info("测试log4j !!");
		String result = "hello springMVC !";
		return result;
	}

	@RequestMapping("requestService")
	@ResponseBody
	public JSONObject requestService(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = springMVCService.springService();
		return json;
	}

	@RequestMapping("sendMQToQueryService")
	@ResponseBody
	public void sendMQToQueryService(HttpServletRequest request,
			HttpServletResponse response) {
		mqSender.send("集成mq和mysql！");
	}

	@RequestMapping("sendMQToTopicService")
	@ResponseBody
	public void sendMQToTopicService(HttpServletRequest request,
			HttpServletResponse response) {
		mqTopicSendServer.sendMessage("发送广播 ！");
	}

	@RequestMapping("sendElasticsearch")
	@ResponseBody
	public void sendElasticsearch(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		json.put("name", "helong");
		json.put("code", "200");
		json.put("work", "programmer");
		json.put("remarks", "备注！");

		try {
			esDao.insertAlertProcessings("alare1", "log", "19940717", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("sendHttpTool")
	@ResponseBody
	public void sendHttpTool(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String str = HttpTool.post3(
					"http://10.0.17.19:8080/data-sync-up/check.do", "");
			LOGGER.info("str : {}", str);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("AOPCtrl")
	@ResponseBody
	public void AOPCtrl(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		json.put("page", "1");
		json.put("size", "10");
		json.put("currectPage", "5");
		springMVCService.AOPService("helong", "123456", json);
	}

}
