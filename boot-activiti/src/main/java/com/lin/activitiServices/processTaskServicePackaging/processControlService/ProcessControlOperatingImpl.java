/**
 * <p>Description: 实现类</p>
 */
package com.lin.activitiServices.processTaskServicePackaging.processControlService;

import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * <p>Description: 此类用于实现流程操作方法</p>
 */
@Service("ProcessControlOperatingInte")
public class ProcessControlOperatingImpl implements ProcessControlOperatingInte{

	/* (non-Javadoc)
	 * <p>Title: startUpProcess</p>
	 * <p>Description: 此方法提供流程启动服务:流程定义Id启动方式/消息触发启动方式</p> 
	 * @param runtimeService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param startSwitch 流程启动的方法标识
	 * @param vars 流程启动需要初始化的参数集 
	 * tips:
	 * 		1. startSwitch:{
	 * 			"byId":"processDefinitionId" / "byMessage":"messages"
	 * 		}
	 * 
	 * 		2. vars:{
	 * 			"varName":"varValue"
	 * 		}
	 * @see com.lin.activitiServices.processTaskServicePackaging.processControlService.ProcessControlOperatingInte#startUpProcess(org.activiti.engine.RuntimeService, java.util.Map, java.util.Map)  
	 */
	public void startUpProcess(RuntimeService runtimeService, Map<String, String> startSwitch, 
			Map<String, Object> vars) {
		// TODO Auto-generated method stub
		for(String key : startSwitch.keySet()) {
			if(key.equals("byId")) {
				if(vars.containsKey("default")) {
					runtimeService.startProcessInstanceById(startSwitch.get(key));
				}else {
					runtimeService.startProcessInstanceById(startSwitch.get(key),vars);
				}
				
			}else {
				if(vars.isEmpty()) {
					runtimeService.startProcessInstanceByMessage(startSwitch.get(key));
				}else {
					runtimeService.startProcessInstanceByMessage(startSwitch.get(key),vars);
				}
			}
		}
		
	}

	/* (non-Javadoc)
	 * <p>Title: performTriggertasks</p>
	 * <p>Description: 此方法提供流程中等待触发类任务节点的完成方法</p> 
	 * @param runtimeService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param receiveId 待触发的执行流Id
	 * @param processVariables 需要设置到流程域的参数列表  
	 * @see com.lin.activitiServices.processTaskServicePackaging.processControlService.ProcessControlOperatingInte#performTriggertasks(org.activiti.engine.RuntimeService, java.lang.String, java.util.Map)  
	 */
	public void performTriggertasks(RuntimeService runtimeService, String receiveId,
			Map<String, Object> processVariables) {
		// TODO Auto-generated method stub
		Execution execution = runtimeService.createExecutionQuery().activityId(receiveId).singleResult(); 
		
		if(processVariables.containsKey("default")) {
			runtimeService.trigger(execution.getId());
		}else {
			runtimeService.trigger(execution.getId(), processVariables);
		}	
	}

	/* (non-Javadoc)
	 * <p>Title: performSingnaltasks</p>
	 * <p>Description: 此方法提供信号捕获事件的完成</p> 
	 * @param runtimeService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param singnalId 待触发的信号捕获节点的Id
	 * @param processVariables 需要设置到流程域的参数列表
	 * @see com.lin.activitiServices.processTaskServicePackaging.processControlService.ProcessControlOperatingInte#performSingnaltasks(org.activiti.engine.RuntimeService, java.lang.String)  
	 */
	public void performSignaltasks(RuntimeService runtimeService, String signalId,
			Map<String, Object> processVariables) {
		// TODO Auto-generated method stub
		Execution execution = runtimeService.createExecutionQuery().activityId(signalId).singleResult();
		
		if(processVariables.containsKey("default")) {
			runtimeService.signalEventReceived(execution.getId());
		}else {
			runtimeService.signalEventReceived(execution.getId(), processVariables);
		}
	}

	/* (non-Javadoc)
	 * <p>Title: performMessagetasks</p>
	 * <p>Description: 此方法提供消息事件的完成</p> 
	 * @param runtimeService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param messageId 待接收消息的任务节点Id
	 * @param processVariables 需要设置到流程域的参数列表
	 * @see com.lin.activitiServices.processTaskServicePackaging.processControlService.ProcessControlOperatingInte#performMessagetasks(org.activiti.engine.RuntimeService, java.lang.String, java.lang.String)  
	 */
	public void performMessagetasks(RuntimeService runtimeService, String messageId, 
			Map<String, Object> processVariables) {
		// TODO Auto-generated method stub
		Execution execution = runtimeService.createExecutionQuery().activityId(messageId).singleResult();
		
		if(processVariables.containsKey("default")) {
			runtimeService.messageEventReceived(execution.getName(), execution.getId());
		}else {
			runtimeService.messageEventReceived(execution.getName(), execution.getId(), processVariables);
		}
	}


	/* (non-Javadoc)
	 * <p>Title: dropRuntimeProcess</p>
	 * <p>Description: 此方法提供删除运行时的流程实例</p> 
	 * @param runtimeService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processInstanceId 待删除的流程实例定义Id
	 * @param deleteReason 删除该流程实例的原因描述 
	 * @see com.lin.activitiServices.processTaskServicePackaging.processControlService.ProcessControlOperatingInte#dropRuntimeProcess(org.activiti.engine.RuntimeService, java.lang.String, java.lang.String)  
	 */
	public void dropRuntimeProcess(RuntimeService runtimeService, String processInstanceId, String deleteReason) {
		// TODO Auto-generated method stub
		runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
	}

}
