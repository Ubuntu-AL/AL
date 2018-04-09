/**
 * <p>Description: </p>
 */
package com.lin.webMC.jsonBeans;

/**
 * @author admin
 * <p>Description: </p>
 */
public class StartSwitch {

	private String name;
	
	private String val;

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
	 * @return the val
	 */
	public String getVal() {
		return val;
	}

	/**
	 * @param val the val to set
	 */
	public void setVal(String val) {
		this.val = val;
	}

	/**
	 * <p>Description: </p>
	 */
	public StartSwitch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>Description: </p>
	 * @param name
	 * @param val
	 */
	public StartSwitch(String name, String val) {
		super();
		this.name = name;
		this.val = val;
	}
}
