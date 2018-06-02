package com.es.imp;

import org.elasticsearch.action.index.IndexResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.es.ESUtils;
import com.es.EsDao;

@Repository
public class EsDaoImpl implements EsDao {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(EsDaoImpl.class);

	public void insertAlertProcessings(String index, String type, String _id,
			JSONObject json) throws Exception {
		try {
			IndexResponse response = ESUtils.client
					.prepareIndex(index, type, _id).setSource(json).execute()
					.actionGet();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
