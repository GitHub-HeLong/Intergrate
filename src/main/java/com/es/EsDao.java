package com.es;

import com.alibaba.fastjson.JSONObject;

public interface EsDao {

	public void insertAlertProcessings(String index, String type, String _id,
			JSONObject json) throws Exception;

}
