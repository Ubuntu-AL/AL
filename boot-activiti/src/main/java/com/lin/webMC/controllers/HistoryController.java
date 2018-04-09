/**
 * <p>Description: </p>
 */
package com.lin.webMC.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lin.activitiServices.processTaskServicePackaging.historyService.HistroyOperatingInte;
import com.lin.webMC.jsonBeans.RequestVars;
import com.lin.webMC.jsonBeans.StartSwitch;
import com.lin.webMC.jsonBeans.Vars;
import com.lin.webMC.pojo.ListsMerge;
import com.lin.webMC.pojo.ReflectUtil;
import com.lin.webMC.pojo.ResponseBodyPackaging;
/**
 * @author admin
 * <p>Description: </p>
 */
@RestController
public class HistoryController {

	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private HistroyOperatingInte historyOperating;
	
	@Autowired
	private ListsMerge listMerge;
	
	@Autowired
	private ResponseBodyPackaging responsebodyPackaging;
	
	@Autowired
	private ReflectUtil reflectUtil;
	
	/**
	 * <p>Title: getProcessHistory</p>  
	 * <p>Description: 此服务方法用于获取工作流历史数据</p> 
	 * @param reqeustVars 该字典标识查询的条件
	 * @return response 返回按照条件查询到的历史数据对象列表
	 * tips:
	 * 		startSwitch:{
	 * 			"byKey":"processInstanceId" / "byMessage":"messages"
	 * 		}
	 */
	@PostMapping("/history/getProcessHistory")
	@ResponseBody
	public Map<String, Object> getProcessHistory(@RequestBody RequestVars requestVars){
		if(requestVars.getVars().isEmpty()) {
			Vars defaultVar = new Vars("default", "default");
			requestVars.getVars().add(defaultVar);
		}
		
		Map<String, Object> vars = new HashMap<String, Object>();
		for(Vars var : requestVars.getVars()) {
			vars.put(var.getName(),var.getVar());
		}
		
		List<List<HistoricProcessInstance>> resoults = new ArrayList<List<HistoricProcessInstance>>();
		
		for(StartSwitch startSwitch : requestVars.getStartSwitch()) {
			Map<String, Object> queryType = new HashMap<String, Object>();
			List<HistoricProcessInstance> resoult = new ArrayList<HistoricProcessInstance>();
			
			queryType.put(startSwitch.getName(), startSwitch.getVal());
			resoult = historyOperating.getProcessHistory(historyService, queryType);
			resoults.add(resoult);
		}
		
		@SuppressWarnings("unchecked")
		List<HistoricProcessInstance> temporary = (ArrayList<HistoricProcessInstance>)listMerge.operating(resoults);
		@SuppressWarnings("unchecked")
		Map<String, HistoricProcessInstance> temporarymap = (HashMap<String, HistoricProcessInstance>)responsebodyPackaging.listPackaging(temporary);
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		for(String key : temporarymap.keySet()) {
			response.put(key, reflectUtil.reflect(temporarymap.get(key)));
		}
		
		return response;
	}
	
	
	/**
	 * <p>Title: getTaskHistory</p>  
	 * <p>Description: 此服务方法用于获取任务历史数据</p> 
	 * @param requestVars 该字典用于标识查询条件，可有多个条件
	 * @return response 返回按条件查询所得的任务历史的对象列表
	 * tips:
	 * 		queryType:{
	 * 			"all":"default"
	 * 			"finished":"default"
	 * 			"unfinished":"default"
	 * 			"processDefinitionId":"processDefinitionId"
	 * 		}
	 */
	@PostMapping("/history/getTaskHistory")
	@ResponseBody
	public Map<String, Object> getTaskHistory(@RequestBody RequestVars requestVars){
		if(requestVars.getVars().isEmpty()) {
			Vars defaultVar = new Vars("default", "default");
			requestVars.getVars().add(defaultVar);
		}
		
		Map<String, Object> vars = new HashMap<String, Object>();
		for(Vars var : requestVars.getVars()) {
			vars.put(var.getName(),var.getVar());
		}
		
		List<List<HistoricTaskInstance>> resoults = new ArrayList<List<HistoricTaskInstance>>();
		
		for(StartSwitch startSwitch : requestVars.getStartSwitch()) {
			Map<String, Object> queryType = new HashMap<String, Object>();
			List<HistoricTaskInstance> resoult = new ArrayList<HistoricTaskInstance>();
			
			queryType.put(startSwitch.getName(), startSwitch.getVal());
			resoult = historyOperating.getTaskHistory(historyService, queryType);
			resoults.add(resoult);
		}
		
		@SuppressWarnings("unchecked")
		List<HistoricTaskInstance> temporary = (ArrayList<HistoricTaskInstance>)listMerge.operating(resoults);
		@SuppressWarnings("unchecked")
		Map<String, HistoricTaskInstance> temporarymap = (HashMap<String, HistoricTaskInstance>)responsebodyPackaging.listPackaging(temporary);
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		for(String key : temporarymap.keySet()) {
			response.put(key, reflectUtil.reflect(temporarymap.get(key)));
		}
		
		return response;
	}
	
	/**
	 * <p>Title: getActivityHistory</p>  
	 * <p>Description: 该服务方法用于获得操作历史数据</p> 
	 * @param requestVars 该字典用于标识查询条件，可有多个条件
	 * @return response 返回按条件查询所得的操作历史的对象列表
	 * tips:
	 * 		queryType:{
	 * 			"all":"default"
	 * 			"finished":"default"
	 * 			"unfinished":"default"
	 * 			"processDefinitionId":"processDefinitionId"
	 * 			"processDefinitionName":"processDefinitionName"
	 * 			"processInstanceId":"processInstanceId"
	 * 			"taskDefinitionKey":"taskAssignee"
	 * 			"taskDueBefore":"taskAssignee"
	 * 			"taskDueAfter":"taskAssignee"
	 * 		}
	 */
	@PostMapping("/history/getActivityHistory")
	@ResponseBody
	public Map<String, Object> getActivityHistory(@RequestBody RequestVars requestVars){
		if(requestVars.getVars().isEmpty()) {
			Vars defaultVar = new Vars("default", "default");
			requestVars.getVars().add(defaultVar);
		}
		
		Map<String, Object> vars = new HashMap<String, Object>();
		for(Vars var : requestVars.getVars()) {
			vars.put(var.getName(),var.getVar());
		}
		
		List<List<HistoricActivityInstance>> resoults = new ArrayList<List<HistoricActivityInstance>>();
		
		for(StartSwitch startSwitch : requestVars.getStartSwitch()) {
			Map<String, String> queryType = new HashMap<String, String>();
			List<HistoricActivityInstance> resoult = new ArrayList<HistoricActivityInstance>();
			
			queryType.put(startSwitch.getName(), startSwitch.getVal());
			resoult = historyOperating.getActivityHistory(historyService, queryType);
			resoults.add(resoult);
		}
		
		@SuppressWarnings("unchecked")
		List<HistoricActivityInstance> temporary = (ArrayList<HistoricActivityInstance>)listMerge.operating(resoults);
		@SuppressWarnings("unchecked")
		Map<String, HistoricActivityInstance> temporarymap = (HashMap<String, HistoricActivityInstance>)responsebodyPackaging.listPackaging(temporary);
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		for(String key : temporarymap.keySet()) {
			response.put(key, reflectUtil.reflect(temporarymap.get(key)));
		}
		
		return response;
		
	}
	
	/**
	 * <p>Title: getDetailHistory</p>  
	 * <p>Description: 此服务方法用于获取工作流历史明细</p> 
	 * @param processInstanceId 待查询明细的工作流定义Id
	 * @return response 返回查询所得的历史明细对象列表
	 * tips:
	 * 		queryType:{
	 * 			"all":"default"
	 * 			"finished":"default"
	 * 			"unfinished":"default"
	 * 			"activityId":"activityId"
	 * 			"activityType":"activityType"
	 * 			"processInstanceId":"processInstanceId"
	 * 			"taskAssignee":"taskAssignee"
	 * 		}
	 */
	@GetMapping("/history/getDetailHistory")
	@ResponseBody
	public Map<String, Object> getDetailHistory(@RequestParam(value = "processInstanceId") String processInstanceId){
		List<HistoricDetail> details = historyOperating.getDetailHistory(historyService, processInstanceId);
		@SuppressWarnings("unchecked")
		Map<String, HistoricDetail> temporary = (HashMap<String, HistoricDetail>)responsebodyPackaging.listPackaging(details);
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		for(String key : temporary.keySet()) {
			response.put(key, reflectUtil.reflect(temporary.get(key)));
		}
		
		return response;
	}
	
	/**
	 * <p>Title: deleteHistory</p>  
	 * <p>Description: 此服务方法用于删除指定的历史数据</p> 
	 * @param requestVars 此字典用于标识删除的历史数据类型，并携带删除所需的参数
	 * tips:
	 * 		requestVars:{
	 * 			"processInstance":"processInstanceId"
	 *			/"taskInstance":"taskInstanceId"
	 * 		}
	 */
	@PostMapping("/history/deleteHistory")
	@ResponseBody
	public void deleteHistory(@RequestBody RequestVars requestVars) {
		if(requestVars.getVars().isEmpty()) {
			Vars defaultVar = new Vars("default", "default");
			requestVars.getVars().add(defaultVar);
		}
		
		Map<String, Object> vars = new HashMap<String, Object>();
		for(Vars var : requestVars.getVars()) {
			vars.put(var.getName(),var.getVar());
		}
		historyOperating.deleteHistory(historyService, vars);
	}
}
