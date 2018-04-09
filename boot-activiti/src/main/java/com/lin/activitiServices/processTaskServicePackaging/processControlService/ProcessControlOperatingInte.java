/**
 * <p>Description: 接口类</p>
 */
package com.lin.activitiServices.processTaskServicePackaging.processControlService;

import java.util.Map;

import org.activiti.engine.RuntimeService;

/**
 * @author admin
 * <p>Description: 此类用于定义流程操作方法</p>
 */
public interface ProcessControlOperatingInte {

	/**
	 * <p>Title: startUpProcess</p>  
	 * <p>Description: 此方法提供流程启动服务</p> 
	 * @param runtimeService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param startSwitch 流程启动的方法标识
	 * @param vars 流程启动需要初始化的参数集
	 * tips:
	 * 		1. startSwitch:{
	 * 			"byKey":"processInstanceId" / "byMessage":"messages"
	 * 		}
	 * 
	 * 		2. vars:{
	 * 			"varName":"varValue"
	 * 		}
	 */
	public void startUpProcess(RuntimeService runtimeService, Map<String, String> startSwitch, Map<String, Object> vars);
	
	/**
	 * <p>Title: performTriggertasks</p>  
	 * <p>Description: 此方法提供流程中等待触发类任务节点的完成方法</p> 
	 * @param runtimeService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param receiveId 待触发的执行流Id
	 * @param processVariables 需要设置到流程域的参数列表
	 */
	public void performTriggertasks(RuntimeService runtimeService, String receiveId, Map<String, Object> processVariables);
	
	/**
	 * <p>Title: performSingnaltasks</p>  
	 * <p>Description: 此方法提供信号捕获事件的完成</p> 
	 * @param runtimeService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param singnalId 待触发的信号捕获节点的Id
	 * @param processVariables 需要设置到流程域的参数列表
	 */
	public void performSignaltasks(RuntimeService runtimeService, String signalId, Map<String, Object> processVariables);
	
	/**
	 * <p>Title: performMessagetasks</p>  
	 * <p>Description: 此方法提供消息事件的完成</p> 
	 * @param runtimeService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param messageId 待接收消息的任务节点Id
	 * @param processVariables 需要设置到流程域的参数列表
	 */
	public void performMessagetasks(RuntimeService runtimeService, String messageId, Map<String, Object> processVariables);
	
	/**
	 * <p>Title: dropRuntimeProcess</p>  
	 * <p>Description: 此方法提供删除运行时的流程实例</p> 
	 * @param runtimeService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processInstanceId 待删除的流程实例定义Id
	 * @param deleteReason 删除该流程实例的原因描述
	 */
	public void dropRuntimeProcess(RuntimeService runtimeService, String processInstanceId, String deleteReason);
}
