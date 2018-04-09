/**
 * <p>Description: 实现类</p>
 */
package com.lin.activitiServices.processTaskServicePackaging.processService;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.IdentityLink;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * <p>Description: 此类用于实现流程信息操作方法</p>
 */
@Service("ProcessOperatingInte")
public class ProcessOperatingImpl implements ProcessOperatingInte {
	
	/* (non-Javadoc)
	 * <p>Title: getProcessDefinitionInfo</p>
	 * <p>Description: 此方法用于根据流程存储Id查询对应流程信息</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param deploymentId 待查询信息的流程部署Id
	 * @return processDefinition 返回单个流程信息的对象 
	 * @see com.lin.activitiServices.processTaskServicePackaging.processService.ProcessOperatingInte#getProcessDefinitionInfoCmd(org.activiti.engine.RepositoryService, java.lang.String)  
	 */
	public ProcessDefinition getProcessDefinitionInfo(RepositoryService repositoryService, String deploymentId) {
		// TODO Auto-generated method stub
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
		return processDefinition;
		
		/* processDefinition.getDeploymentId():	返回流程部署的id
		 * processDefinition.getDescription():	返回流程描述
		 * processDefinition.getId():			返回流程定义表中的主键值
		 * processDefinition.getKey():			返回流程定义的名称，流程的唯一名称
		 * processDefinition.getName():			返回流程定义显示的名称
		 * processDefinition.getResourceName(): 在流程部署的时，会将流程的xml文件存储到资源表中，此方法返回该xml资源的名称
		 * processDefinition.getTenantId():		返回租户Id
		 * processDefinition.isSuspended():		判断流程装填，是否处于中断状态（1为激活状态，2为中断状态）
		 * */
	}

	/* (non-Javadoc)
	 * <p>Title: processPowerSwitch</p>
	 * <p>Description: 此方法用于流程激活/中止状态的切换，流程状态开关</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processDefinitionKey 待更改状态的流程定义名（流程唯一的名称字段 key） 
	 * @see com.lin.activitiServices.processTaskServicePackaging.processService.ProcessOperatingInte#processPowerSwitch(org.activiti.engine.RepositoryService, java.lang.String)  
	 */
	public void processPowerSwitch(RepositoryService repositoryService, String processDefinitionKey) {
		// TODO Auto-generated method stub
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).singleResult();
		
		if(processDefinition.isSuspended()) {
			repositoryService.activateProcessDefinitionByKey(processDefinitionKey);
		}else {
			repositoryService.suspendProcessDefinitionByKey(processDefinitionKey);
		}
	}

	/* (non-Javadoc)
	 * <p>Title: bindUser</p>
	 * <p>Description: 此方法用于绑定流程定义的用户，为用户启动流程定义的权限验证提供支持</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processDefinitionId 待绑定用户的流程定义的Id
	 * @param userId 待被绑定的用户Id  
	 * @see com.lin.activitiServices.processTaskServicePackaging.processService.ProcessOperatingInte#bindUser(org.activiti.engine.RepositoryService, java.lang.String, java.lang.String)  
	 */
	public void bindUser(RepositoryService repositoryService, String processDefinitionId, String userId) {
		// TODO Auto-generated method stub
		repositoryService.addCandidateStarterUser(processDefinitionId, userId);
	}
	/* (non-Javadoc)
	 * <p>Title: unbindUser</p>
	 * <p>Description: 此方法用于解绑流程定义的用户，为用户启动流程定义的权限验证提供支持</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processDefinitionId 待解绑用户的流程定义的Id
	 * @param userId 待解绑的用户Id 
	 * @see com.lin.activitiServices.processTaskServicePackaging.processService.ProcessOperatingInte#unbindUser(org.activiti.engine.RepositoryService, java.lang.String, java.lang.String)  
	 */
	public void unbindUser(RepositoryService repositoryService, String processDefinitionId, String userId) {
		// TODO Auto-generated method stub
		repositoryService.deleteCandidateStarterUser(processDefinitionId, userId);
	}

	/* (non-Javadoc)
	 * <p>Title: bindGroup</p>
	 * <p>Description: 此方法用于绑定流程定义的用户，为用户组启动流程定义的权限验证提供支持</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processDefinitionId 待绑定用户的流程定义的Id
	 * @param groupId 待被绑定的用户组Id 
	 * @see com.lin.activitiServices.processTaskServicePackaging.processService.ProcessOperatingInte#bindGroup(org.activiti.engine.RepositoryService, java.lang.String, java.lang.String)  
	 */
	public void bindGroup(RepositoryService repositoryService, String processDefinitionId, String groupId) {
		// TODO Auto-generated method stub
		repositoryService.addCandidateStarterGroup(processDefinitionId, groupId);
	}
	
	/* (non-Javadoc)
	 * <p>Title: unbindGroup</p>
	 * <p>Description: 此方法用于解绑流程定义的用户组，为用户组启动流程定义的权限验证提供支持</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processDefinitionId 待解绑用户的流程定义的Id
	 * @param groupId 待解绑的用户组Id
	 * @see com.lin.activitiServices.processTaskServicePackaging.processService.ProcessOperatingInte#unbindGroup(org.activiti.engine.RepositoryService, java.lang.String, java.lang.String)  
	 */
	public void unbindGroup(RepositoryService repositoryService, String processDefinitionId, String groupId) {
		// TODO Auto-generated method stub
		repositoryService.deleteCandidateStarterGroup(processDefinitionId, groupId);
	}

	/* (non-Javadoc)
	 * <p>Title: getDefinitedProcesses</p>
	 * <p>Description: 此方法用于 查询指定用户下拥有多少绑定可定义的流程定义对象</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param userId 待查询流程定义的用户Id
	 * @return processDefinitions 返回已绑定该用户的所有流程定义的对象列表
	 * @see com.lin.activitiServices.processTaskServicePackaging.processService.ProcessOperatingInte#getDefinitedProcesses(org.activiti.engine.RepositoryService, java.lang.String)  
	 */
	public List<ProcessDefinition> getDefinitedProcesses(RepositoryService repositoryService, String userId) {
		// TODO Auto-generated method stub
		List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().startableByUser(userId).list();
		return processDefinitions;
	}

	/* (non-Javadoc)
	 * <p>Title: getProcessInfo</p>
	 * <p>Description: 此方法用于查询指定流程定义中的所有身份数据</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processDefinitionId 待查询流程身份数据的流程定义Id
	 * @return identityLinks 返回流程定义中所有身份数据的对象列表
	 * @see com.lin.activitiServices.processTaskServicePackaging.processService.ProcessOperatingInte#getProcessInfo(org.activiti.engine.RepositoryService, java.lang.String)  
	 */
	public List<IdentityLink> getProcessInfo(RepositoryService repositoryService, String processDefinitionId) {
		// TODO Auto-generated method stub
		List<IdentityLink> identityLinks = repositoryService.getIdentityLinksForProcessDefinition(processDefinitionId);
		return identityLinks;
	}

	/* (non-Javadoc)
	 * <p>Title: dropProcess</p>
	 * <p>Description: 此方法用于删除已部署或正在执行的流程资源</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param deploymentId 待删除的流程资源部署Id 
	 * @see com.lin.activitiServices.processTaskServicePackaging.processService.ProcessOperatingInte#dropProcess(org.activiti.engine.RepositoryService, java.lang.String)  
	 */
	public void dropProcess(RepositoryService repositoryService, String deploymentId) {
		// TODO Auto-generated method stub
		//参数二，布尔类型参数表示，是否进行级联删除，所谓级联删除就是已激活的流程产生了任务数据等多个表数据，删除是一并清理掉。true是一并清理；
		//若设为false或者不填写该参数，则默认不进行级联删除，对于未激活的流程依然可以正常执行删除，但是对已激活的流程则会报错
		repositoryService.deleteDeployment(deploymentId, true);
	}
	
	/* (non-Javadoc)
	 * <p>Title: getDeployment</p>
	 * <p>Description: 此方法用于获取已部署的流程资源</p> 
	 * @param repositoryService 为保证服务类的单例性，从业务函数获取服务实例
	 * @return depoyments 返回流程部署资源的对象列表
	 * @see com.lin.activitiServices.processTaskServicePackaging.processService.ProcessOperatingInte#getDeployment(org.activiti.engine.RepositoryService)  
	 */
	public List<Deployment> getDeployment(RepositoryService repositoryService){
		List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
		return deployments;
	}
}
