/**
 * <p>Description: json数据Bean类</p>
 */
package com.lin.webMC.jsonBeans;

import java.util.Map;

import org.activiti.engine.identity.User;

/**
 * @author admin
 * <p>Description: 此类用于封装用户和用户信息内容以供返回信息使用</p>
 */
public class ResponseUser {
	private String name;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	private User user;
	
	private Map<String, String> userInfo;
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the userInfo
	 */
	public Map<String, String> getUserInfo() {
		return userInfo;
	}

	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(Map<String, String> userInfo) {
		this.userInfo = userInfo;
	}
	
	
}
