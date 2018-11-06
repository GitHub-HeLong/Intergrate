package com.mysqlDao.mysqlImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
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

	@Override
	public boolean update(String eventNumId) {
		boolean falg = false;
		String sql = "UPDATE request_records SET mark_place=3 WHERE id='"
				+ eventNumId + "'";
		try {
			jdbctemplate.execute(sql);
			falg = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			falg = false;
		}
		return falg;
	}

}
