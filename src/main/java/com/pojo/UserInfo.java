package com.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * mongoDB以对象形式查询数据
 */
@Document(collection = "user_info")
public class UserInfo {
	@Id
	@Field(value = "_id")
	@Indexed
	private String userId;

	@Indexed(unique = true)
	private String userName;

	private String nickName;// 昵称

	private String password;

	private String realName;

	private String userNo;

	private String email;

	private String phone;

	private Integer userType;

	private String userAddr;

	private String userDesc;

	@Indexed
	private String parentId = "0";

	private String registrationCode;

	private String sex;

	private Integer age;

	private Integer workYear;

	private String path;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date workDate;

	private Map<String, List<String>> operationRights;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date loginTime;

	public UserInfo() {
		super();
	}

	public UserInfo(String userName, String password, String realName,
			String userNo, String email, String phone, Integer userType,
			String userAddr, String userDesc, String parentId,
			String registrationCode) {
		super();
		this.userName = userName;
		this.password = password;
		this.realName = realName;
		this.userNo = userNo;
		this.email = email;
		this.phone = phone;
		this.userType = userType;
		this.userAddr = userAddr;
		this.userDesc = userDesc;
		this.parentId = parentId;
		this.registrationCode = registrationCode;
	}

	public UserInfo(String userName, String password, String realName,
			String userNo, String email, String phone, Integer userType,
			String userAddr, String userDesc, String parentId,
			String registrationCode, String nickName) {
		super();
		this.userName = userName;
		this.password = password;
		this.realName = realName;
		this.userNo = userNo;
		this.email = email;
		this.phone = phone;
		this.userType = userType;
		this.userAddr = userAddr;
		this.userDesc = userDesc;
		this.parentId = parentId;
		this.registrationCode = registrationCode;
		this.nickName = nickName;
	}

	public UserInfo(String userId, String userName, String nickName,
			String password, String realName, String userNo, String email,
			String phone, Integer userType, String userAddr, String userDesc,
			String parentId, String registrationCode, String sex, Integer age,
			Integer workYear, String path, Date workDate,
			Map<String, List<String>> operationRights, Date loginTime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.nickName = nickName;
		this.password = password;
		this.realName = realName;
		this.userNo = userNo;
		this.email = email;
		this.phone = phone;
		this.userType = userType;
		this.userAddr = userAddr;
		this.userDesc = userDesc;
		this.parentId = parentId;
		this.registrationCode = registrationCode;
		this.sex = sex;
		this.age = age;
		this.workYear = workYear;
		this.path = path;
		this.workDate = workDate;
		this.operationRights = operationRights;
		this.loginTime = loginTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Map<String, List<String>> getOperationRights() {
		return operationRights;
	}

	public void setOperationRights(Map<String, List<String>> operationRights) {
		this.operationRights = operationRights;
	}

	public String getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getWorkYear() {
		return workYear;
	}

	public void setWorkYear(Integer workYear) {
		this.workYear = workYear;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			System.out.println("125");
			this.workDate = sdf.parse(workDate);
		} catch (ParseException e) {
			System.out.println("126");
			this.workDate = new Date();
			e.printStackTrace();
		}
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName
				+ ", nickName=" + nickName + ", password=" + password
				+ ", realName=" + realName + ", userNo=" + userNo + ", email="
				+ email + ", phone=" + phone + ", userType=" + userType
				+ ", userAddr=" + userAddr + ", userDesc=" + userDesc
				+ ", parentId=" + parentId + ", registrationCode="
				+ registrationCode + ", sex=" + sex + ", age=" + age
				+ ", workYear=" + workYear + ", path=" + path + ", workDate="
				+ workDate + "]";
	}
}
