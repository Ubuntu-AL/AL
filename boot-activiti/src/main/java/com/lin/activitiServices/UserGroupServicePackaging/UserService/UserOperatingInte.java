/**
 * 
 */
package com.lin.activitiServices.UserGroupServicePackaging.UserService;


import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

/**
 * <p>Title: UserOperatingInte</p>  
 * <p>Description: 此类用于定义用户操作方法</p>  
 * @author Lin 
 * @date 2018年3月17日 
 */
public interface UserOperatingInte {

	
	/**
	 * <p>Title: modifyUser</p>  
	 * <p>Description: 用于修改已有用户的用户信息</p> 
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param userId 待修改信息的用户Id
	 * @param firstName 用户姓氏字段
	 * @param lastName 用户名字字段
	 * @param email 用户邮箱地址
	 * @param password 用户密码
	 * @return user 修改后的用户对象
	 */
	public User modifyUser(IdentityService identityService, String userId, String firstName, String lastName, String email, String password);
	
	/**
	 * <p>Title: createSingleUser</p>
	 * <p>Description: 用于创建单个用户</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param firstName 用户姓氏
	 * @param lastName 用户名字
	 * @param email 用户Email地址
	 * @param password 用户密码
	 * @return User 返回一个已注册的用户对象
	 */
	public User singleCreateUser(IdentityService identityService, String firstName, String lastName, String email, String password);
	
	/**
	 * <p>Title: singleDropUser</p>
	 * <p>Description: 用于删除单个用户</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param id 用户的Id
	 */
	public void singleDropUser(IdentityService identityService, String id);
	
	/**
	 * <p>Title: batchDropUse</p>
	 * <p>Description: 用于批量删除用户</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param ids 用户id的列表
	 */
	public void batchDropUser(IdentityService identityService, List<String> ids);
	
	/**
	 * <p>Title: allUser</p>
	 * <p>Description: 用于获取所有用户</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @return users 所用已注册的用户对象列表 
	 */
	public List<User> allUser(IdentityService identityService);
	
	/**
	 * <p>Title: fuzzyQueryByName</p>  
	 * <p>Description: 此方法使用用户姓氏或名字单条件查找或组合条件查找用户组</p> 
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param firstName 待查询的用户姓氏
	 * @param lastName 待查询的用户名字
	 * @return users 返回模糊查询所得的所有用户对象列表
	 */
	public List<User> fuzzyQueryByName(IdentityService identityService, String firstName, String lastName);
	
	/**
	 * <p>Title: checkUserPassword</p>
	 * <p>Description: 用于验证用户密码</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param userId 用户Id
	 * @param password 用户密码
	 * @return check 验证结果
	 */
	public Boolean checkUserPassword(IdentityService identityService, String userId, String password);
	
	
	/**
	 * <p>Title: allGroups</p>  
	 * <p>Description: 用于为用户查询所属的所有用户组</p> 
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param user 待查询的用户对象
	 * @return groups 查询所得的所有用户组对象列表
	 */ 
	public List<Group> allGroups(IdentityService identityService, String userId);
	
	/**
	 * <p>Title: getStartableUsers</p>  
	 * <p>Description: 此方法用于查找指定流程定义所绑定的所有用户</p> 
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processDefinitionId 待查询绑定情况的流程定义Id
	 * @return users 返回已绑定该流程定义的所有用户的对象列表
	 */
	public List<User> getStartableUsers(IdentityService identityService, String processDefinitionId);
}
