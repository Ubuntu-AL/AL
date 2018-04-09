/**
 * <p>Description: 接口类</p>
 */
package com.lin.activitiServices.processTaskServicePackaging.taskService;

import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Event;
import org.activiti.engine.task.Task;

/**
 * @author admin
 * <p>Description: 此类用于定义任务操作方法</p>
 */
public interface TaskOperatingInte {

	/**
	 * <p>Title: dropTask</p>  
	 * <p>Description: 此方法用于删除指定任务</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待删除的任务Id
	 */
	public void dropTask(TaskService taskService, String taskId);
	
	/**
	 * <p>Title: dropTasks</p>  
	 * <p>Description: 此方法用于批量删除任务</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskIds 待删除的任务Id的列表
	 */
	public void dropTasks(TaskService taskService, List<String> taskIds);
	
	/**
	 * <p>Title: bindingGroup</p>  
	 * <p>Description: 此方法用于为任务绑定用户组，同时将用户组的用户绑定为候选用户</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待绑定用户组的任务Id
	 * @param groupId 待绑定的用户组Id
	 */
	public void bindingGroup(TaskService taskService, IdentityService identityService, String taskId,String groupId);
	
	/**
	 * <p>Title: unbindingGroup</p>  
	 * <p>Description: 此方法用于为任务解绑用户组，同时将用户组的用户从候选用户中解绑</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待解绑用户组的任务Id
	 * @param groupId 待解绑的用户组Id
	 */
	public void unbindingGroup(TaskService taskService, IdentityService identityService, String taskId, String groupId);
	
	/**
	 * <p>Title: setOwner</p>  
	 * <p>Description: 此方法设置任务的持有人</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param userId 待绑定成持有人的用户Id
	 * @param taskId 待绑定持有人的任务Id
	 */
	public void setOwner(TaskService taskService, String userId, String taskId);
	
	/**
	 * <p>Title: setAssignee</p>  
	 * <p>Description: 此方法设置任务的受理人</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param userId 待绑定成受理人的用户Id
	 * @param taskId 待绑定持有人的任务Id
	 */
	public void setAssignee(TaskService taskService, String userId, String taskId);
	
	/**
	 * <p>Title: getMyTasks</p>  
	 * <p>Description: 此方法用于获取指定受理人的所有任务</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param assignee 待查询任务的受理人名称
	 * @return tasks 返回查询到的所有任务的对象列表
	 */
	public List<Task> getMyTasks(TaskService taskService, String assignee);
	
	/**
	 * <p>Title: setVariables</p>  
	 * <p>Description: 此方法用于设置任务的参数字段,该方法设置的参数作用于任务所属的整个流程</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param variables 待设置的参数字典，key=varType, value = varValue
	 * @param taskId 待设置参数的任务Id
	 * tips:
	 * 		1. Object参数可承载的通用包装类型：Boolean, Date, Double, Integer, Long, Short, String， Null;
	 * 		2. Object参数可承载的一般对象类型:理论上可以承载任何实现序列化接口(Serializable)的类,
	 * 		       但是此处由于是设置任务属性，故不宜使用内容过于复杂的大类;
	 */
	public void setVariables(TaskService taskService, Map<String, Object> variables, String taskId);
	
	/**
	 * <p>Title: setVariablesLocal</p>  
	 * <p>Description: 此方法用于设置任务的参数字段,该方法设置的参数仅作用于所属任务内</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param variables 待设置的参数字典，key=varType, value = varValue
	 * @param taskId 待设置参数的任务Id
	 * tips:
	 * 		1. Object参数可承载的通用包装类型：Boolean, Date, Double, Integer, Long, Short, String， Null;
	 * 		2. Object参数可承载的一般对象类型:理论上可以承载任何实现序列化接口(Serializable)的类,
	 * 		       但是此处由于是设置任务属性，故不宜使用内容过于复杂的大类;
	 */
	public void setVariablesLocal(TaskService taskService, Map<String, Object> variables, String taskId);
	
	/**
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
	 */
	public Map<String, Object> getVariables(TaskService taskService, String taskId);
	
	/**
	 * <p>Title: getAllVariables</p>  
	 * <p>Description: 此方法用于获取指定任务的所有参数，即作用域仅限于任务级的参数</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待获取参数的任务Id
	 * @return variables 返回所有参数的列表
	 */
	public Map<String, Object> getAllVariablesLocal(TaskService taskService, String taskId);
	
	/**
	 * <p>Title: createAttachment</p>  
	 * <p>Description: 此方法用于为指定流程下的指定任务创建附件</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param attachmentVars 创建附件所需的参数列表
	 * attachmentVars:{
	 * 		"attachmentType":"";		文件类型，仅为标准，没有限制文件格式的功能
	 * 		"taskId":"";				待创建附件的任务Id
	 * 		"processInstanceId":""; 	待创建附件的任务所在的流程Id
	 * 		"attachmentName":"";		创建的附件文件名称
	 *		"attachmentDescription":""; 附件文件的概要描述
	 *		"url":"";					附件文件上传后的存储地址
	 * }
	 */
	public void createAttachment(TaskService taskService, Map<String , String> attachmentVars);
	
	/**
	 * <p>Title: deleteAttachment</p>  
	 * <p>Description: 此方法用于删除指定的附件</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param attachmentId 待删除的附件Id
	 */
	public void deleteAttachment(TaskService taskService, String attachmentId);
	
	/**
	 * <p>Title: getProcessAttachment</p>  
	 * <p>Description: 此方法获取指定流程的所有附件</p> 
	 * @param taskServiceInte 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processIstanceId 待获取附件的流程Id
	 * @return attachments 返回附件对象列表
	 */
	public List<Attachment> getProcessAttachment(TaskService taskService, String processIstanceId);
	
	/**
	 * <p>Title: getTaskAttachment</p>  
	 * <p>Description: 此方法获取指定任务的所有附件</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待获取附件的任务Id
	 * @return attachments 返回附件的对象列表
	 */
	public List<Attachment> getTaskAttachment(TaskService taskService, String taskId);
	
	/**
	 * <p>Title: setTaskComment</p>  
	 * <p>Description: 此方法为指定任务添加评论</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param commentVars 添加任务评论所需要的参数列表
	 * commentVars:{
	 * 		"taskId":""				待添加评论的任务Id
	 * 		"processIstanceId":""	待添加评论的任务所在的流程Id
	 * 		"message":""			评论的内容
	 * }
	 */
	public void setTaskComment(TaskService taskService, Map<String , String> commentVars);
	
	/**
	 * <p>Title: getComment</p>  
	 * <p>Description: 此方法获取指定的评论内容</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param commentId 待获取的评论Id
	 * @return comment 返回指定id的评论对象
	 */
	public Comment getComment(TaskService taskService, String commentId);
	
	/**
	 * <p>Title: getTaskComments</p>  
	 * <p>Description: 此方法获取指定任务的评论集合</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待获取评论的任务Id
	 * @return comments 返回查询到的所有评论对象列表
	 */
	public List<Comment> getTaskComments(TaskService taskService, String taskId);
	
	/**
	 * <p>Title: getTaskEvents</p>  
	 * <p>Description: 此方法获取指定任务的事件集合</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待获取事件的任务Id
	 * @return events 返回查询到的所有事件对象列表
	 */
	public List<Event> getTaskEvents(TaskService taskService, String taskId);
	
	/**
	 * <p>Title: taskDelegation</p>  
	 * <p>Description: 此方法类似设置任务代理人，用于指定任务的处理人</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待委派用户的任务Id
	 * @param userId 待委派任务的用户Id
	 * tips:
	 * 		类似：该方法使用了claim方法指定任务代理人，于setAssignee方法类似
	 * 		不同：同一个任务，claim方法只能使用一次，若再次使用claim方法更换同一个任务的代理人会抛出异常
	 */
	public void taskDelegation(TaskService taskService, String taskId, String userId);
	
	/**
	 * <p>Title: finishTask</p>  
	 * <p>Description: 此方法用于完成指定任务，具备带完成带参数的任务的能力</p> 
	 * @param taskService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param taskId 待完成的任务Id
	 * @param vars 参数的列表
	 */
	public void finishTask(TaskService taskService, String taskId, Map<String ,Object> vars);
}
