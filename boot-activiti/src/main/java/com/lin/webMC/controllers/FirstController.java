/**
 * 
 */
package com.lin.webMC.controllers;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author admin
 *
 */
@RestController
public class FirstController {
	
	@Autowired
	RepositoryService repositiryService;
	
	@Autowired
	TaskService taskService;
	
	@GetMapping("/hello")
	@ResponseBody
	public void welcom() {
		ProcessDefinition pd = repositiryService.createProcessDefinitionQuery().deploymentId("27501").singleResult();
		Task task = taskService.createTaskQuery().processDefinitionId("Check_and_Accept_Process:6:27504").singleResult();
		
		System.out.println(repositiryService.createDeploymentQuery().count());
		System.out.println(taskService.createTaskQuery().list());
		System.out.println(pd.getId());
		System.out.println(task.getId());
	}
}
