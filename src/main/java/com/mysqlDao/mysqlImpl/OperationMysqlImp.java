package com.mysqlDao.mysqlImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysqlDao.OperationMysql;

@Repository
public class OperationMysqlImp implements OperationMysql {

	@Resource
	private JdbcTemplate jdbctemplate;

	@Override
	public List<Map<String, Object>> queryDate() {

		String sql = "SELECT * FROM imm_camera LIMIT 0,10";
		List<Map<String, Object>> list = jdbctemplate.queryForList(sql);
		return list;
	}

}
