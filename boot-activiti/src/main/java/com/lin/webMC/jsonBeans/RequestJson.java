/**
 * <p>Description: json数据Bean类</p>
 */
package com.lin.webMC.jsonBeans;

import java.util.List;

/**
 * @author admin
 * <p>Description: 此类用于接收json请求体中对应的数据对象</p>
 */
public class RequestJson {
	
	private String groupId;
	
	private List<String> userIds;

	/**
	 * <p>Description: </p>
	 */
	public RequestJson() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>Description: </p>
	 * @param groupId
	 * @param userIds
	 */
	public RequestJson(String groupId, List<String> userIds) {
		super();
		this.groupId = groupId;
		this.userIds = userIds;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the userIds
	 */
	public List<String> getUserIds() {
		return userIds;
	}

	/**
	 * @param userIds the userIds to set
	 */
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

}
