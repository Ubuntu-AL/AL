/**
 * <p>Description: 接口类</p>
 */
package com.lin.activitiServices.processTaskServicePackaging.processService;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.IdentityLink;

/**
 * @author admin
 * <p>Description: 此类用于定义流程信息操作方法</p>
 */
public interface ProcessOperatingInte {
	
	/**
	 * <p>Title: getProcessDefinitionInfo</p>  
	 * <p>Description: 此方法用于根据流程部署Id查询对应流程信息</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param deploymentId 待查询信息的流程Id
	 * @return processDefinition 返回单个流程信息的对象
	 */
	public ProcessDefinition getProcessDefinitionInfo(RepositoryService repositoryService, String deploymentId);
	
	/**
	 * <p>Title: processPowerSwitch</p>  
	 * <p>Description: 此方法用于流程激活/中止状态的切换，流程状态开关</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processDefinitionKey 待更改状态的流程定义名（流程唯一的名称字段 key）
	 */
	public void processPowerSwitch(RepositoryService repositoryService, String processDefinitionKey);
	
	/**
	 * <p>Title: bindUser</p>  
	 * <p>Description: 此方法用于绑定流程定义的用户，为用户启动流程定义的权限验证提供支持</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processDefinitionId 待绑定用户的流程定义的Id
	 * @param userId 待被绑定的用户Id
	 */
	public void bindUser(RepositoryService repositoryService, String processDefinitionId, String userId);
	
	/**
	 * <p>Title: unbindUser</p>  
	 * <p>Description: 此方法用于解绑流程定义的用户，为用户启动流程定义的权限验证提供支持</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processDefinitionId 待解绑用户的流程定义的Id
	 * @param userId 待解绑的用户Id
	 */
	public void unbindUser(RepositoryService repositoryService, String processDefinitionId, String userId);
	
	/**
	 * <p>Title: bindGroup</p>  
	 * <p>Description: 此方法用于绑定流程定义的用户，为用户组启动流程定义的权限验证提供支持</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processDefinitionId 待绑定用户的流程定义的Id
	 * @param groupId 待被绑定的用户组Id
	 */
	public void bindGroup(RepositoryService repositoryService, String processDefinitionId, String groupId);
	
	/**
	 * <p>Title: unbindGroup</p>  
	 * <p>Description: 此方法用于解绑流程定义的用户，为用户组启动流程定义的权限验证提供支持</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processDefinitionId 待解绑用户的流程定义的Id
	 * @param groupId 待解绑的用户组Id
	 */
	public void unbindGroup(RepositoryService repositoryService, String processDefinitionId, String groupId);
	
	/**
	 * <p>Title: getDefinitedProcesses</p>  
	 * <p>Description: 此方法用于 查询指定用户下拥有多少绑定可定义的流程定义对象</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param userId 待查询流程定义的用户Id
	 * @return processDefinitions 返回已绑定该用户的所有流程定义的对象列表
	 */
	public List<ProcessDefinition> getDefinitedProcesses(RepositoryService repositoryService, String userId);
	
	/**
	 * <p>Title: getProcessInfo</p>  
	 * <p>Description: 此方法用于查询指定流程定义中的所有身份数据</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processDefinitionId 待查询流程身份数据的流程定义Id
	 * @return identityLinks 返回流程定义中所有身份数据的对象列表
	 */
	public List<IdentityLink> getProcessInfo(RepositoryService repositoryService, String processDefinitionId);
	
	/**
	 * <p>Title: dropProcess</p>  
	 * <p>Description: 此方法用于删除已部署或正在执行的流程资源</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param deploymentId 待删除的流程资源部署Id
	 */
	public void dropProcess(RepositoryService repositoryService, String deploymentId);
	
	/**
	 * <p>Title: getDeployment</p>  
	 * <p>Description: 此方法用于获取已部署的流程资源</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @return depoyments 返回流程部署资源的对象列表
	 */
	public List<Deployment> getDeployment(RepositoryService repositoryService);
}
