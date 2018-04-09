/**
 * <p>Description: 实现类</p>
 */
package com.lin.activitiServices.processTaskServicePackaging.taskService;

import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Event;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * <p>Description: 此类用于实现任务操作方法</p>
 */
@Service("TaskOperatingInte")
public class TaskOperatingImpl implements TaskOperatingInte {

	/* (non-Javadoc)
	 * <p>Title: dropTask</p>
	 * <p>Description: 此方法用于删除指定任务</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待删除的任务Id 
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#dropTask(org.activiti.engine.TaskService, java.lang.String)  
	 */
	public void dropTask(TaskService taskService, String taskId) {
		// TODO Auto-generated method stub
		taskService.deleteTask(taskId,true);
	}

	/* (non-Javadoc)
	 * <p>Title: dropTasks</p>
	 * <p>Description: 此方法用于批量删除任务</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskIds 待删除的任务Id的列表  
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#dropTasks(org.activiti.engine.TaskService, java.util.List)  
	 */
	public void dropTasks(TaskService taskService, List<String> taskIds) {
		// TODO Auto-generated method stub
		taskService.deleteTasks(taskIds, true);
	}

	/* (non-Javadoc)
	 * <p>Title: bindingGroup</p>
	 * <p>Description: 此方法用于为任务绑定用户组，同时将用户组的用户绑定为候选用户</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待绑定用户组的任务Id
	 * @param groupId 待绑定的用户组Id  
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#bindingGroup(org.activiti.engine.TaskService, org.activiti.engine.IdentityService, java.lang.String, java.lang.String)  
	 */
	public void bindingGroup(TaskService taskService, IdentityService identityService, String taskId, String groupId) {
		// TODO Auto-generated method stub
		taskService.addCandidateGroup(taskId, groupId);
		
		List<User> users = identityService.createUserQuery().memberOfGroup(groupId).list();
		
		for(User user : users) {
			taskService.addCandidateUser(taskId, user.getId());
		}
		
	}

	/* (non-Javadoc)
	 * <p>Title: unbindingGroup</p>
	 * <p>Description: 此方法用于为任务解绑用户组，同时将用户组的用户从候选用户中解绑</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待解绑用户组的任务Id
	 * @param groupId 待解绑的用户组Id 
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#unbindingGroup(org.activiti.engine.TaskService, org.activiti.engine.IdentityService, java.lang.String, java.lang.String)  
	 */
	public void unbindingGroup(TaskService taskService, IdentityService identityService, String taskId,
			String groupId) {
		// TODO Auto-generated method stub
		taskService.deleteCandidateGroup(taskId, groupId);
		
		List<User> users = identityService.createUserQuery().memberOfGroup(groupId).list();
		
		for(User user : users) {
			taskService.deleteCandidateUser(taskId, user.getId());
		}
		
	}

	/* (non-Javadoc)
	 * <p>Title: setOwner</p>
	 * <p>Description: 此方法设置任务的持有人</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param userId 待绑定成持有人的用户Id,任务持有人不具备任务的执行权限，只具备任务的委派权限，即重置任务的受理人
	 * @param taskId 待绑定持有人的任务Id 
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#setOwner(org.activiti.engine.TaskService, java.lang.String, java.lang.String)  
	 */
	public void setOwner(TaskService taskService, String userId, String taskId) {
		// TODO Auto-generated method stub
		taskService.setOwner(taskId, userId);
	}

	/* (non-Javadoc)
	 * <p>Title: setAssignee</p>
	 * <p>Description: 此方法设置任务的受理人</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param userId 待绑定成受理人的用户Id，任务的受理人，具备任务的执行权限，具有签收委派任务的方法，并只能执行任务
	 * @param taskId 待绑定持有人的任务Id 
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#setAssignee(org.activiti.engine.TaskService, java.lang.String, java.lang.String)  
	 */
	public void setAssignee(TaskService taskService, String userId, String taskId) {
		// TODO Auto-generated method stub
		taskService.setAssignee(taskId, userId);
	}
	
	/* (non-Javadoc)
	 * <p>Title: getMyTasks</p>
	 * <p>Description: 此方法用于获取指定受理人的所有任务</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param assignee 待查询任务的受理人名称
	 * @return tasks 返回查询到的所有任务的对象列表
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#getMyTasks(org.activiti.engine.TaskService, java.lang.String)  
	 */
	public List<Task> getMyTasks(TaskService taskService, String assignee) {
		// TODO Auto-generated method stub
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
		
		return tasks;
	}

	/* (non-Javadoc)
	 * <p>Title: setVariables</p>
	 * <p>Description: 此方法用于设置任务的参数字段,该方法设置的参数作用于任务所属的整个流程</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param variables 待设置的参数字典，key=varType, value = varValue
	 * @param taskId 待设置参数的任务Id
	 * tips:
	 * 		1. Object参数可承载的通用包装类型：Boolean, Date, Double, Integer, Long, Short, String， Null;
	 * 		2. Object参数可承载的一般对象类型:理论上可以承载任何实现序列化接口(Serializable)的类,
	 * 		       但是此处由于是设置任务属性，故不宜使用内容过于复杂的大类; 
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#setVariables(org.activiti.engine.TaskService, java.util.Map, java.lang.String)  
	 */
	public void setVariables(TaskService taskService, Map<String, Object> variables, String taskId) {
		// TODO Auto-generated method stub
		taskService.setVariables(taskId, variables);
	}
	
	/* (non-Javadoc)
	 * <p>Title: setVariablesLocal</p>
	  * <p>Description: 此方法用于获取任务的参数列表，该方法提取的只有作用于整个流程域内的参数</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待查询参数列表的任务Id
	 * @return variables 返回查询到的任务参数字典，key=varType ,value = varValue
	 * tips:
	 * 		1. 提取Object值时，需要自行对值进行强制类型转换
	 * 		
	 * 		例如:map{"name":"Lin"}
	 * 			String name = (String) map.get("name"); 
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#setVariablesLocal(org.activiti.engine.TaskService, java.util.Map, java.lang.String)  
	 */
	public void setVariablesLocal(TaskService taskService, Map<String, Object> variables, String taskId) {
		// TODO Auto-generated method stub
		taskService.setVariablesLocal(taskId, variables);
	}

	/* (non-Javadoc)
	 * <p>Title: getVariables</p>
	 * <p>Description: 此方法用于获取任务的参数列表，该方法提取的只有作用于整个流程域内的参数</p>  
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待查询参数列表的任务Id
	 * @return variables 返回查询到的任务参数字典，key=varType ,value = varValue
	 * tips:
	 * 		1. 提取Object值时，需要自行对值进行强制类型转换
	 * 		
	 * 		例如:map{"name":"Lin"}
	 * 			String name = (String) map.get("name");
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#getVariables(org.activiti.engine.TaskService, java.util.List, java.lang.String)  
	 */
	public Map<String, Object> getVariables(TaskService taskService , String taskId) {
		// TODO Auto-generated method stub	
		Map<String, Object> variables = taskService.getVariables(taskId);
		
		return variables;
	}

	/* (non-Javadoc)
	 * <p>Title: getAllVariables</p>
	 * <p>Description: 此方法用于获取指定任务的所有参数，即作用域仅限于任务级的参数</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待获取参数的任务Id
	 * @return variables 返回所有参数的列表
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#getAllVariables(org.activiti.engine.TaskService, java.lang.String)  
	 */
	public Map<String, Object> getAllVariablesLocal(TaskService taskService, String taskId) {
		// TODO Auto-generated method stub
		Map<String, Object> variables = taskService.getVariablesLocal(taskId);
		
		return variables;
	}

	/* (non-Javadoc)
	 * <p>Title: createAttachment</p>
	 * <p>Description: </p>
	 * @param attachmentVars 创建附件所需的参数列表
	 * attachmentVars:{
	 * 		"attachmentType":"";		文件类型，仅为标准，没有限制文件格式的功能
	 * 		"taskId":"";				待创建附件的任务Id
	 * 		"processInstanceId":""; 	待创建附件的任务所在的流程Id
	 * 		"attachmentName":"";		创建的附件文件名称
	 *		"attachmentDescription":""; 附件文件的概要描述
	 *		"url":"";					附件文件上传后的存储地址
	 * }
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#createAttachment(org.activiti.engine.TaskService, java.lang.String, java.lang.String, java.lang.String, java.lang.String)  
	 */
	public void createAttachment(TaskService taskService, Map<String, String> attachmentVars) {
		// TODO Auto-generated method stub
		taskService.createAttachment(attachmentVars.get("attachmentType"), 
									 attachmentVars.get("taskId"), 
									 attachmentVars.get("processInstanceId"), 
									 attachmentVars.get("attachmentName"), 
									 attachmentVars.get("attachmentDescription"), 
									 attachmentVars.get("url"));
	}

	/* (non-Javadoc)
	 * <p>Title: deleteAttachment</p>
	 * <p>Description: 此方法用于删除指定的附件</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param attachmentId 待删除的附件Id  
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#deleteAttachment(org.activiti.engine.TaskService, java.lang.String)  
	 */
	public void deleteAttachment(TaskService taskService, String attachmentId) {
		// TODO Auto-generated method stub
		taskService.deleteAttachment(attachmentId);
	}
	
	/* (non-Javadoc)
	 * <p>Title: getProcessAttachment</p>
	 * <p>Description: 此方法获取指定流程的所有附件</p> 
	 * @param taskServiceInte 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processIstanceId 待获取附件的流程Id
	 * @return attachments 返回附件对象列表
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#getProcessAttachment(com.lin.processTaskServicePackaging.taskService.TaskServiceInte, java.lang.String)  
	 */
	public List<Attachment> getProcessAttachment(TaskService taskService, String processIstanceId) {
		// TODO Auto-generated method stub
		List<Attachment> attachments = taskService.getProcessInstanceAttachments(processIstanceId);
		
		return attachments;
	}

	/* (non-Javadoc)
	 * <p>Title: getTaskAttachment</p>
	 * <p>Description: 此方法获取指定任务的所有附件</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待获取附件的任务Id
	 * @return attachments 返回附件的对象列表  
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#getTaskAttachment(org.activiti.engine.TaskService, java.lang.String)  
	 */
	public List<Attachment> getTaskAttachment(TaskService taskService, String taskId) {
		// TODO Auto-generated method stub
		List<Attachment> attachments = taskService.getTaskAttachments(taskId);
		
		return attachments;
	}

	/* (non-Javadoc)
	 * <p>Title: setTaskComment</p>
	 * <p>Description: 此方法为指定任务添加评论</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param commentVars 添加任务评论所需要的参数列表
	 * commentVars:{
	 * 		"taskId":""				待添加评论的任务Id
	 * 		"processIstanceId":""	待添加评论的任务所在的流程Id
	 * 		"message":""			评论的内容
	 * } 
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#setTaskComment(org.activiti.engine.TaskService, java.util.Map)  
	 */
	public void setTaskComment(TaskService taskService, Map<String, String> commentVars) {
		// TODO Auto-generated method stub
		
		taskService.addComment(commentVars.get("taskId"), 
							   commentVars.get("processInstanceId"), 
							   commentVars.get("message"));
	}

	/* (non-Javadoc)
	 * <p>Title: getComment</p>
	 * <p>Description: 此方法获取指定的评论内容</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param commentId 待获取的评论Id
	 * @return comment 返回指定id的评论对象
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#getComment(org.activiti.engine.TaskService, java.lang.String)  
	 */
	public Comment getComment(TaskService taskService, String commentId) {
		// TODO Auto-generated method stub
		Comment comment = taskService.getComment(commentId);
		
		return comment;
	}

	/* (non-Javadoc)
	 * <p>Title: getTaskComments</p>
	 * <p>Description: 此方法获取指定任务的评论集合</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待获取评论的任务Id
	 * @return comments 返回查询到的所有评论对象列表
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#getTaskComments(org.activiti.engine.TaskService, java.lang.String)  
	 */
	public List<Comment> getTaskComments(TaskService taskService, String taskId) {
		// TODO Auto-generated method stub
		List<Comment> comments = taskService.getTaskComments(taskId);
		
		return comments;
	}

	/* (non-Javadoc)
	 * <p>Title: getTaskEvents</p>
	 * <p>Description: 此方法获取指定任务的事件集合</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待获取事件的任务Id
	 * @return events 返回查询到的所有事件对象列表  
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#getTaskEvents(org.activiti.engine.TaskService, java.lang.String)  
	 */
	public List<Event> getTaskEvents(TaskService taskService, String taskId) {
		// TODO Auto-generated method stub
		List<Event> events = taskService.getTaskEvents(taskId);
		
		return events;
	}

	/* (non-Javadoc)
	 * <p>Title: taskDelegation</p>
	 * <p>Description: 此方法类似设置任务代理人，用于指定任务的处理人</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待委派用户的任务Id
	 * @param userId 待委派任务的用户Id
	 * tips:
	 * 		类似：该方法使用了claim方法指定任务代理人，于setAssignee方法类似
	 * 		不同：同一个任务，claim方法只能使用一次，若再次使用claim方法更换同一个任务的代理人会抛出异常 
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#taskDelegation(org.activiti.engine.TaskService, java.lang.String, java.lang.String)  
	 */
	public void taskDelegation(TaskService taskService, String taskId, String userId) {
		// TODO Auto-generated method stub
		taskService.claim(taskId, userId);
	}

	/* (non-Javadoc)
	 * <p>Title: finishTask</p>
	 * <p>Description: 此方法用于完成指定任务，具备带完成带参数的任务的能力</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待完成的任务Id
	 * @param vars 参数的列表 ，若没有参数，请设置为空
	 * @see com.lin.activitiServices.processTaskServicePackaging.taskService.TaskOperatingInte#finishTask(org.activiti.engine.TaskService, java.util.Map)  
	 */
	public void finishTask(TaskService taskService, String taskId, Map<String, Object> vars) {
		// TODO Auto-generated method stub
		if(vars.isEmpty()) {
			taskService.complete(taskId);
		}else {
			taskService.complete(taskId, vars);
		}
	}
}
