/**
 * <p>Description: </p>
 */
package com.lin.webMC.jsonBeans;

import org.activiti.engine.identity.Group;

/**
 * @author admin
 * <p>Description: </p>
 */
public class ResponseGroup {

	private String name;
	
	private Group group;

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

	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
	}
}
