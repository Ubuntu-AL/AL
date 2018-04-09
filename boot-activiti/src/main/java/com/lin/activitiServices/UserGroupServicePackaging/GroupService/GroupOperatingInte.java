package com.lin.activitiServices.UserGroupServicePackaging.GroupService;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

/**
 * <p>Title: GroupOperatingInte</p>  
 * <p>Description: 此类用于定义用户组操作方法</p>  
 * @author Lin 
 * @date 2018年3月17日 
 */

public interface GroupOperatingInte {
	
	/**
	 * <p>Title: modifyGroup</p>  
	 * <p>Description: 该方法用于修改用户组信息</p> 
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param groupid 待修改信息的用户组ID
	 * @param name 用户组名字段
	 * @param type 用户组类型字段
	 * @return group 返回用户组对象
	 */
	public Group modifyGroup(IdentityService identityService, String groupId, String groupName, String type);
	
	/**
	 * <p>Title: createGroup</p>
	 * <p>Description: 该方法用于创建单个用户组</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param name 用户组名
	 * @param type 用户组类型
	 * @return group 返回用户组对象
	 */
	public Group singleCreateGroup(IdentityService identityService, String name, String type) ;
	
	/**
	 * <p>Title: batchCreateGroup</p>
	 * <p>Description: 该方法用于批量创建用户组</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param fields 该参数为一个Map，key为用户组的Type属性，value为用户组的name属性
	 * @return groupIds 该返回值key为用户组的name,value为对应用户组的id
	 */
	public Map<String, String> batchCreateGroup(IdentityService identityService, Map<String, String> fields);
	
	/**
	 * <p>Title: singleDropGroup</p>
     * <p>Description: 用于删除单个用户组</p>
     * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
     * @param id 用户组的id
	 */
	public void singleDropGroup(IdentityService identityService, String id);
	
	/**
	 * <p>Title: batchDropGroup</p>
     * <p>Description: 用于批量删除用户组</p>
     * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
     * @param groupIds 用户组id的集合
	 */
	public void batchDropGroup(IdentityService identityService, List<String> groupIds);
	
	/**
	 * <p>Title: singleQueryGroup</p>
	 * <p>Description: 用于按照组名名称，单个查询该组的Id</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param  用户组组名
	 * @return group 查询所得的用户组实例，若未查询到相应组名的数据，则返回Error
	 */
	public Group singleQueryGroup(IdentityService identityService, String name);
	
	/**
	 * <p>Title: batchQueryGroup</p>
	 * <p>Description: 根据传入的用户组名列表，查询列表内所有用户组的Id</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param names 用户组组名的列表
	 * @return groups 查询所得的所有用户组对象，若有未查询到的组名，则List中value被置为null
	 */
	public List<Group> batchQueryGroup(IdentityService identityService, List<String> names);
	

	/**
	 * <p>Title: allGroups</p>
	 * <p>Description: 用于汇总已存在的所有用户组信息</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @return groups 查询所得的所有用户组的信息列表
	 */
	public List<Group> allGroups(IdentityService identityService);
	
	/**
	 * <p>Title: listPageGroups</p>
	 * <p>Description: 用于分页抽查用户组信息</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param firstResult 查询的起始位置，最小从0开始
	 * @param maxResult 查询的总数
	 * @return groups 查询所得的用户组信息列表，若查询数量越界，或者用户组不存在则返回null空列表
	 */
	public List<Group> listPageGroups(IdentityService identityService, Integer firstResult, Integer maxResult);
	
	/**
	 * <p>Title: fuzzyQueryByName</p>  
	 * <p>Description: 此方法使用用户名模糊查找用户组</p> 
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param groupName 待模糊查询的用户组名
	 * @return groups 返回模糊查询所得的所有用户组对象列表
	 */
	public List<Group> fuzzyQueryByName(IdentityService identityService, String groupName);
	
	/**
	 * <p>Title: createMemberShip</p>  
	 * <p>Description: 用于为用户组添加用户，绑定用户组与用户关系</p> 
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param userId 待绑定的用户Id
	 * @param groupId 待绑定的用户组Id
	 */
	public void createMemberShip(IdentityService identityService, String userId, String groupId);
	
	/**
	 * <p>Title: dropMemberShip</p>  
	 * <p>Description: 用于为用户组删除用户，解绑用户组与用户关联</p> 
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param userId 待解绑的用户Id
	 * @param groupId 待解绑的用户组Id
	 */
	public void dropMemberShip(IdentityService identityService, String userId, String groupId);
	
	/**
	 * <p>Title: allMembers</p>  
	 * <p>Description: 用于查询用户组下的所有关联用户</p> 
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param groupId 待查询的用户组Id
	 * @return users 查询所得的用户组下所有绑定的用户对象列表
	 */
	public List<User> allMembers(IdentityService identityService, String groupId);
	
	/**
	 * <p>Title: getStartableGroups</p>  
	 * <p>Description: 此方法用于获取指定流程定义所绑定的所有用户组</p> 
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processDefinitionId 待查询绑定情况的流程定义Id
	 * @return groups 返回已经绑定该流程定义的所有用户组的对象列表
	 */
	public List<Group> getStartableGroups(IdentityService identityService, String processDefinitionId);
}
