/**
 * <p>Description: </p>
 */
package com.lin.webMC.jsonBeans;

/**
 * @author admin
 * <p>Description: </p>
 */
public class Vars {

	private String name;
	
	private Object var;

	/**
	 * <p>Description: </p>
	 */
	public Vars() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>Description: </p>
	 * @param var
	 * @param name
	 */
	public Vars(String var, String name) {
		super();
		this.var = var;
		this.name = name;
	}

	/**
	 * @return the var
	 */
	public Object getVar() {
		return var;
	}

	/**
	 * @param var the var to set
	 */
	public void setVar(String var) {
		this.var = var;
	}
	
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

}
