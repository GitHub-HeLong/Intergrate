package com.es;

import org.elasticsearch.action.search.SearchResponse;

import com.alibaba.fastjson.JSONObject;

public interface EsDao {

	public void insertAlertProcessings(String index, String type, String _id,
			JSONObject json) throws Exception;

	public SearchResponse fetchBykey(String index);

}
