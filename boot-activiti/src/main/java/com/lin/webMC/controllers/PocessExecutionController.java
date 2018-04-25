/**
 * <p>Description: 控制器类</p>
 */
package com.lin.webMC.controllers;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//import com.lin.activitiServices.UserGroupServicePackaging.GroupService.GroupOperatingInte;
//import com.lin.activitiServices.UserGroupServicePackaging.UserService.UserOperatingInte;
import com.lin.activitiServices.processTaskServicePackaging.processControlService.ProcessControlOperatingInte;
import com.lin.activitiServices.processTaskServicePackaging.processService.ProcessOperatingInte;
import com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte;
import com.lin.webMC.jsonBeans.AttachmentVars;
import com.lin.webMC.jsonBeans.RequestVars;
import com.lin.webMC.jsonBeans.StartSwitch;
import com.lin.webMC.jsonBeans.Vars;
import com.lin.webMC.pojo.ReflectUtil;

/**
 * @author admin
 * <p>Description: 次控制器内，提供工作流资源查询、工作流启动、个人任务查询、完成四类任务的服务方法</p>
 */
@RestController
public class PocessExecutionController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private RepositoryService repositoryService;
	
//	@Autowired
//	private UserOperatingInte userOperating;
//	
//	@Autowired
//	private GroupOperatingInte groupOperating;
	
	@Autowired
	private TaskOperatingInte taskOperating;
	
	@Autowired
	private ProcessOperatingInte processOperating;
	
	@Autowired
	private ProcessControlOperatingInte processControlOperating;
	
	@Autowired
	private ReflectUtil reflectUtil;
	
	

	/**
	 * <p>Title: getAllProcesses</p>  
	 * <p>Description: 此服务用于获取所有的流程定义资源，可供启动</p> 
	 * @return responseData 返回查询得到的所有已部署的资源
	 */
	@GetMapping("/pocessExecution/getAllDeployments")
	@ResponseBody
	public Map<String, Object> getAllDeployments(){
		List<Deployment> deployments = processOperating.getDeployment(repositoryService);
		System.out.println(deployments.toString());
		Map<String, Object> responseData = new HashMap<String, Object>();
		int i = 1;
		
		for(Deployment deployment : deployments) {
			Map<String, Object> resoult = reflectUtil.reflect(deployment);
			responseData.put("deployment"+i, resoult);
			i++;
		}
		
		System.out.println(responseData.toString());
		return responseData;
	}
	
	/**
	 * <p>Title: getDefinitedProcesses</p>  
	 * <p>Description: 此服务方法获取具有执行权限用户的所有过程定义资源</p> 
	 * @param userId 待查询流程定义资源权限用户Id
	 * @return responseData 返回查询所得的用户具备定义权限的所有任务资源对象列表
	 */
	@GetMapping("/pocessExecution/getDefinitedProcesses")
	@ResponseBody
	public Map<String, Object> getDefinitedProcesses(@RequestParam(value = "userId") String userId){
		List<ProcessDefinition> processDefinitions= processOperating.getDefinitedProcesses(repositoryService, userId);
		
		Map<String, Object> responseData = new HashMap<String, Object>();
		int i = 1;
		
		for(ProcessDefinition processDefinition : processDefinitions) {
			Map<String, Object> resoult = reflectUtil.reflect(processDefinition);
			responseData.put("processDefinition"+i, resoult);
			i++;
		}
		
		return responseData;
	}
	
	/**
	 * <p>Title: startProcess</p>  
	 * <p>Description: 此服务方法用于启动指定的已部署的流程资源</p> 
	 * @param requestVars 启动流程需要添加的参数，必须包含startSwitch
	 */
	@PostMapping("/pocessExecution/startProcess")
	public void startProcess(@RequestBody RequestVars requestVars) {
		Vars defaultVars = new Vars();
		defaultVars.setVar("default");
		
		if(requestVars.getVars().size() == 0) {
			requestVars.getVars().add(defaultVars);
		}
		
		for(StartSwitch startSwitch: requestVars.getStartSwitch()) {
			System.out.println(startSwitch.getName()+": "+startSwitch.getVal());
		}

		Map<String, String> startSwitch = new HashMap<String, String>();
		Map<String, Object> vars = new HashMap<String, Object>();
		for(StartSwitch startSwitch2 : requestVars.getStartSwitch()) {
			ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(startSwitch2.getVal()).singleResult();
			startSwitch.put(startSwitch2.getName(), pd.getId());
		}
		for(Vars var : requestVars.getVars()) {
			vars.put(var.getName(),var.getVar());
		}
		
		processControlOperating.startUpProcess(runtimeService, startSwitch, vars);
	}
	
	/**
	 * <p>Title: getMyTasks</p>  
	 * <p>Description: 此服务方法用于获取指定处理人的所有活动任务</p> 
	 * @param assignee 待查询活动任务的处理人名称
	 * @return responseData 返回查询得到的处理人所有的任务对象列表
	 */
	@GetMapping("/pocessExecution/getMyTasks")
	@ResponseBody
	public Map<String, Object> getMyTasks(@RequestParam(value = "assignee") String assignee){
		List<Task> tasks = taskOperating.getMyTasks(taskService, assignee);
		Map<String, Object> responseData = new HashMap<String, Object>();
		int i = 1;
		
		for(Task task : tasks) {
			Map<String, Object> resoult = reflectUtil.reflect(task);
			responseData.put("task"+i, resoult);
			i++;
		}
		
		return responseData;
	}
	
	/**
	 * <p>Title: completeUserTask</p>  
	 * <p>Description: 此服务方法用于完成指定用户任务</p> 
	 * @param assignee 任务的受理人
	 * @param processDefinitionId 任务所属流程的定义Id
	 * @param requestVars 待添加的流程参数
	 * @return responseData 返回查询得到的处理人所有的任务对象列表
	 */
	@PostMapping("/pocessExecution/completeTask")
	@ResponseBody
	public Map<String, Object> completeUserTask(@RequestBody @RequestParam(value = "assignee") String assignee, 
										  	    @RequestBody @RequestParam(value = "processDefinitionId") String processDefinitionId,
										  	    @RequestBody RequestVars requestVars){
		Task getTaskId = taskService.createTaskQuery().processDefinitionId(processDefinitionId).singleResult();
		Map<String, Object> vars = new HashMap<String, Object>();
		if(requestVars.getVars().isEmpty()) {
			Vars defaultVar = new Vars("default", "default");
			requestVars.getVars().add(defaultVar);
		}else {
			for(Vars var : requestVars.getVars()) {
				vars.put(var.getName(),var.getVar());
			}
			
			runtimeService.setVariables(getTaskId.getId(), vars);
		}	
		
		taskOperating.finishTask(taskService, getTaskId.getId(), vars);
		List<Task> tasks = taskOperating.getMyTasks(taskService, assignee);
		Map<String, Object> responseData = new HashMap<String, Object>();
		int i = 1;
		
		for(Task task : tasks) {
			Map<String, Object> resoult = reflectUtil.reflect(task);
			responseData.put("task"+i, resoult);
			i++;
		}
		
		return responseData;
	}
	
	/**
	 * <p>Title: completeFileTask</p>  
	 * <p>Description: 此服务方法用于完成带附件的用户任务</p> 
	 * @param file 任务附件
	 * @param assignee 任务处理人
	 * @param requestVars 任务需要设置到流程域的参数,必须包含attachmentVars字典对象用于存放文件参数
	 * @return responseData 个人任务列表
	 * attachmentVars:{
	 * 		"attachmentType":"";		文件类型，仅为标准，没有限制文件格式的功能
	 * 		"taskId":"";				待创建附件的任务Id
	 * 		"processInstanceId":""; 	待创建附件的任务所在的流程Id
	 * 		"attachmentName":"";		创建的附件文件名称
	 *		"attachmentDescription":""; 附件文件的概要描述
	 *		"url":"";					附件文件上传后的存储地址(待上传转存之后由本控制器生成后填入)
	 */
	@PostMapping("/pocessExecution/completeFileTask")
	@ResponseBody
	public Map<String, Object> completeFileTask(HttpServletRequest request,
											    @RequestParam(value = "file") MultipartFile file,
											    @RequestBody @RequestParam(value = "assignee") String assignee,
											    @RequestBody AttachmentVars attachmentVars){
		Map<String, String> fileVar = new HashMap<String,String>();
		Map<String, Object> requestVars = new HashMap<String, Object>();
		
		String uploadDir = request.getSession().getServletContext().getRealPath("resource/static/annex/"+attachmentVars.getProcessInstanceId()+"/"+attachmentVars.getTaskId()+"/");
		
		try {	
			
			File serverFile = new File(uploadDir+attachmentVars.getAttachmentName());
			
			File dir = new File(uploadDir);
			if(!dir.exists()) {
				dir.mkdir();
			}
			
			file.transferTo(serverFile);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
		String url = "Http://127.0.0.1:8080"+ uploadDir+attachmentVars.getAttachmentName();
		attachmentVars.setUrl(url);
		
		fileVar.put("attachmentType", attachmentVars.getAttachementType());
		fileVar.put("taskId", attachmentVars.getTaskId());
		fileVar.put("processInstanceId", attachmentVars.getProcessInstanceId());
		fileVar.put("attachmentName", attachmentVars.getAttachmentName());
		fileVar.put("attachmentDescription", attachmentVars.getAttachmentDescription());
		fileVar.put("url", attachmentVars.getUrl());
		
		taskOperating.createAttachment(taskService, fileVar);
		
		taskOperating.finishTask(taskService, attachmentVars.getTaskId(), requestVars);
		
		List<Task> tasks = taskOperating.getMyTasks(taskService, assignee);
		Map<String, Object> responseData = new HashMap<String, Object>();
		int i = 1;
		
		for(Task task : tasks) {
			Map<String, Object> resoult = reflectUtil.reflect(task);
			responseData.put("task"+i, resoult);
			i++;
		}
		
		return responseData;
		
	}
	
	/**
	 * <p>Title: completesignalTask</p>  
	 * <p>Description: 此服务方法用于完成指定信号触发任务</p> 
	 * @param assignee 任务的受理人
	 * @param signalId 待捕获信号的信号任务Id
	 * @param requestVars 待添加的流程参数
	 * @return responseData 返回查询得到的处理人所有的任务对象列表
	 */
	@PostMapping("/pocessExecution/completesignalTask")
	@ResponseBody
	public Map<String, Object> completesignalTask(@RequestBody @RequestParam(value = "assignee") String assignee, 
			  								   	  @RequestBody @RequestParam(value = "signalId") String signalId, 
			  								   	  @RequestBody RequestVars requestVars){
		if(requestVars.getVars().isEmpty()) {
			Vars defaultVar = new Vars("default", "default");
			requestVars.getVars().add(defaultVar);
		}
		
		Map<String, Object> vars = new HashMap<String, Object>();
		for(Vars var : requestVars.getVars()) {
			vars.put(var.getName(),var.getVar());
		}
		
		processControlOperating.performSignaltasks(runtimeService, signalId, vars);
		List<Task> tasks = taskOperating.getMyTasks(taskService, assignee);
		Map<String, Object> responseData = new HashMap<String, Object>();
		int i = 1;
		
		for(Task task : tasks) {
			Map<String, Object> resoult = reflectUtil.reflect(task);
			responseData.put("task"+i, resoult);
			i++;
		}
		
		return responseData;
	}
	
	/**
	 * <p>Title: completeMessageTask</p>  
	 * <p>Description: 此服务方法用于完成指定消息触发任务</p> 
	 * @param assignee 任务的受理人
	 * @param messageId 待接收消息的消息任务Id
	 * @param requestVars 待添加的流程参数
	 * @return responseData 返回查询得到的处理人所有的任务对象列表
	 */
	@PostMapping("/pocessExecution/completeMessageTask")
	@ResponseBody
	public Map<String, Object> completeMessageTask(@RequestBody @RequestParam(value = "assignee") String assignee, 
			   									   @RequestBody @RequestParam(value = "messageId") String messageId, 
			   									   @RequestBody RequestVars requestVars){
		if(requestVars.getVars().isEmpty()) {
			Vars defaultVar = new Vars("default", "default");
			requestVars.getVars().add(defaultVar);
		}
		
		Map<String, Object> vars = new HashMap<String, Object>();
		for(Vars var : requestVars.getVars()) {
			vars.put(var.getName(),var.getVar());
		}
		
		processControlOperating.performMessagetasks(runtimeService, messageId, vars);
		List<Task> tasks = taskOperating.getMyTasks(taskService, assignee);
		Map<String, Object> responseData = new HashMap<String, Object>();
		int i = 1;
		
		for(Task task : tasks) {
			Map<String, Object> resoult = reflectUtil.reflect(task);
			responseData.put("task"+i, resoult);
			i++;
		}
		
		return responseData;
	}
	
	/**
	 * <p>Title: completeMessageTask</p>  
	 * <p>Description: 此服务方法用于完成指定消息触发任务</p> 
	 * @param assignee 任务的受理人
	 * @param messageId 待接收消息的消息任务Id
	 * @param requestVars 待添加的流程参数
	 * @return responseData 返回查询得到的处理人所有的任务对象列表
	 */
	@PostMapping("/pocessExecution/completetTriggerTask")
	@ResponseBody
	public Map<String, Object> completetTriggerTask(@RequestBody @RequestParam(value = "assignee") String assignee, 
			   									    @RequestBody @RequestParam(value = "triggerId") String triggerId, 
			   									    @RequestBody RequestVars requestVars){
		if(requestVars.getVars().isEmpty()) {
			Vars defaultVar = new Vars("default", "default");
			requestVars.getVars().add(defaultVar);
		}
		
		Map<String, Object> vars = new HashMap<String, Object>();
		for(Vars var : requestVars.getVars()) {
			vars.put(var.getName(),var.getVar());
		}
		
		processControlOperating.performTriggertasks(runtimeService, triggerId, vars);
		List<Task> tasks = taskOperating.getMyTasks(taskService, assignee);
		Map<String, Object> responseData = new HashMap<String, Object>();
		int i = 1;
		
		for(Task task : tasks) {
			Map<String, Object> resoult = reflectUtil.reflect(task);
			responseData.put("task"+i, resoult);
			i++;
		}
		
		return responseData;
	}
}
