package com.mongoDBDao;

import java.util.List;

import com.pojo.UserInfo;

public interface IUserInfoDao {

	public UserInfo getUserInfoById(String userId);

	public String getUserById(UserInfo userInfo);

	public UserInfo modifyUserInfo(UserInfo userInfo);

	public boolean deleteUser(String userName);

	public List<UserInfo> getAllUser();

	public UserInfo getUserInfo(String userName, String parentId,
			List<Integer> userType);

	public List<UserInfo> getUserByUserName(String userName,
			List<Integer> userType);

	public boolean modifyPassword(String userName, String password);

}
