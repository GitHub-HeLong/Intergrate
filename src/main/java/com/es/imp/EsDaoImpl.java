package com.es.imp;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
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

			LOGGER.info("response :" + response);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public SearchResponse fetchBykey(String index) {
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		SearchResponse searchResponse = ESUtils.client.prepareSearch(index)
				.setQuery(boolQuery).execute().actionGet();

		return searchResponse;
	}

}
