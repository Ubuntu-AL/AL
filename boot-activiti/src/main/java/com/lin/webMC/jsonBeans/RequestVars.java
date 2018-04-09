/**
 * <p>Description: </p>
 */
package com.lin.webMC.jsonBeans;

import java.util.List;

/**
 * @author admin
 * <p>Description: </p>
 */
public class RequestVars{
	
	private StartSwitch startSwitch;

	private List<Vars> vars;
	
	/**
	 * <p>Description: </p>
	 */
	public RequestVars() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>Description: </p>
	 * @param startSwitch
	 * @param vars
	 */
	public RequestVars(StartSwitch startSwitch, List<Vars> vars) {
		super();
		this.startSwitch = startSwitch;
		this.vars = vars;
	}


	/**
	 * @return the startSwitch
	 */
	public StartSwitch getStartSwitch() {
		return startSwitch;
	}

	/**
	 * @param startSwitch the startSwitch to set
	 */
	public void setStartSwitch(StartSwitch startSwitch) {
		this.startSwitch = startSwitch;
	}

	/**
	 * @return the vars
	 */
	public List<Vars> getVars() {
		return vars;
	}

	/**
	 * @param vars the vars to set
	 */
	public void setVars(List<Vars> vars) {
		this.vars = vars;
	}
	
	
}
