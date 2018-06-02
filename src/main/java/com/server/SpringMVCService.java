package com.server;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.aop.LogAnnotation;
import com.mysqlDao.OperationMysql;

@Service
public class SpringMVCService {

	@Resource
	OperationMysql operationMysql;

	public JSONObject springService() {
		JSONObject json = new JSONObject();

		List<Map<String, Object>> list = operationMysql.queryDate();

		json.put("code", 200);
		json.put("msg", "success");
		json.put("json", list);
		return json;
	}

	@LogAnnotation(whitelog = "200")
	public JSONObject AOPService(String userName, String userId, JSONObject json) {
		JSONObject result = new JSONObject();
		result.put("code", 200);
		result.put("msg", "success");
		result.put("json", json);
		result.put("userName", userName);
		result.put("userId", userId);
		return result;
	}

}
