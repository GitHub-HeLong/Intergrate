package com.ctrl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.action.search.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.es.EsDao;
import com.mongoDBDao.IUserInfoDao;
import com.mq.MQSender;
import com.mq.MqTopicSendServer;
import com.pojo.UserInfo;
import com.server.SpringMVCService;
import com.tool.HttpTool;
import com.tool.HttpUtil;

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

	@Resource
	IUserInfoDao mongodb;

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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:MM:SS");
		Date date = new Date();

		JSONObject json = new JSONObject();
		json.put("sysCode", "E123");
		json.put("eventTime", "2018-06-16 14:18:40");
		json.put("devId", "100001000");
		json.put("devZoneId", "0001");
		json.put("eventSrc", "COM41");
		json.put("devSubSys", "01");

		while (true) {
			mqSender.send(json.toJSONString());
			Date date1 = new Date();
			if (date.getTime() + 1000 <= date1.getTime()) {
				break;
			}
		}
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
			// esDao.insertAlertProcessings("alare1", "log", "19940719", json);

			SearchResponse searchResponse = esDao
					.fetchBykey("alert_processing");

			System.out.println(searchResponse);
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
					"http://10.0.17.19:19531/data-sync-up/check.do",
					"{'name':'HttpToolName'}");
			LOGGER.info("HttpTool : {}", str);

			// 此方法可以设定请求时长和返回时长
			String util = HttpUtil.post(
					"http://10.0.17.19:19531/data-sync-up/check.do",
					"{'name':'HttpUtilName'}");
			LOGGER.info("HttpUtil : {}", util);
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

	@RequestMapping("textMongDB")
	@ResponseBody
	public JSONObject textMongDB(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		UserInfo userInfo = mongodb.getUserInfoById("5a693902a3103d177c65855e");
		json.put("userInfo", userInfo);
		return json;

	}

}
