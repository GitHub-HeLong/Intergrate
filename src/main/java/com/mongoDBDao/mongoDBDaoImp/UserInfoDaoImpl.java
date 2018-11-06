package com.mongoDBDao.mongoDBDaoImp;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongoDBDao.IUserInfoDao;
import com.mongodb.WriteResult;
import com.pojo.UserInfo;

@Component
public class UserInfoDaoImpl implements IUserInfoDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	MongoTemplate mongoTemplate;

	@Override
	public UserInfo getUserInfoById(String userId) {
		try {
			UserInfo userInfo = mongoTemplate.findById(userId, UserInfo.class);
			return userInfo;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public String getUserById(UserInfo userInfo) {
		UserInfo userName;
		try {
			Query query = Query.query(Criteria.where("_id").is(
					userInfo.getUserId()));
			userName = mongoTemplate.findOne(query, UserInfo.class);
			return userName.getUserName();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}

	@Override
	public UserInfo modifyUserInfo(UserInfo userInfo) {
		try {
			Query query = Query.query(Criteria.where("_id").is(
					userInfo.getUserId()));
			Update update = new Update();
			if (userInfo.getRealName() != null)
				update.set("realName", userInfo.getRealName());
			if (userInfo.getNickName() != null) {
				update.set("nickName", userInfo.getNickName());
			}
			if (userInfo.getEmail() != null)
				update.set("email", userInfo.getEmail());
			if (userInfo.getPhone() != null) {
				update.set("phone", userInfo.getPhone());
			}
			if (userInfo.getUserAddr() != null)
				update.set("userAddr", userInfo.getUserAddr());
			if (userInfo.getUserDesc() != null)
				update.set("userDesc", userInfo.getUserDesc());
			if (userInfo.getSex() != null)
				update.set("sex", userInfo.getSex());
			if (userInfo.getAge() != null)
				update.set("age", userInfo.getAge());
			mongoTemplate.updateFirst(query, update, UserInfo.class);
			return userInfo;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public boolean deleteUser(String userName) {
		logger.info("deleteUser userName={}.", userName);
		Query query = Query.query(Criteria.where("userName").is(userName));
		mongoTemplate.remove(query, UserInfo.class);
		return true;
	}

	@Override
	public List<UserInfo> getAllUser() {
		return mongoTemplate.findAll(UserInfo.class);
	}

	@Override
	public UserInfo getUserInfo(String userName, String parentId,
			List<Integer> userType) {
		try {
			Criteria criteria = Criteria.where("userName").is(userName)
					.and("parentId").is(parentId);
			if (userType != null && userType.size() > 0) {
				criteria.andOperator(Criteria.where("userType").in(userType));
			}
			Query query = Query.query(criteria);
			UserInfo userInfo = mongoTemplate.findOne(query, UserInfo.class);
			if (userInfo != null) {
				return userInfo;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<UserInfo> getUserByUserName(String userName,
			List<Integer> userType) {
		try {
			// 正则表达式查询，andOperator里面只能对应一个字段，多个条件限制
			Criteria criteria = Criteria.where("userName").regex(userName);
			if (userType != null && userType.size() > 0) {
				criteria.andOperator(Criteria.where("userType").in(userType));
			}
			Query query = Query.query(criteria);
			List<UserInfo> userInfos = mongoTemplate
					.find(query, UserInfo.class);
			return userInfos;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public boolean modifyPassword(String userName, String password) {
		try {
			Query query = Query.query(Criteria.where("userName").is(userName)
					.and("userType").ne(-1));
			Update update = new Update();
			update.set("password", password);
			WriteResult updateFirst = mongoTemplate.updateFirst(query, update,
					UserInfo.class);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

}
