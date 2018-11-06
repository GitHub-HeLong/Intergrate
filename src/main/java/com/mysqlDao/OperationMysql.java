package com.mysqlDao;

import java.util.List;
import java.util.Map;

public interface OperationMysql {

	public List<Map<String, Object>> queryDate();

	public boolean update(String eventNumId);

}
