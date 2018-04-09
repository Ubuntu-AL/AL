/**
 * 
 */
package com.lin.activitiServices.UserGroupServicePackaging.UserService;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.stereotype.Service;

/**
 * <p>Title: UserOperating</p>  
 * <p>Description: 此类用于实现用户操作方法</p>  
 * @author Lin 
 * @date 2018年3月16日 
 */
@Service("userOperatingInte")
public class UserOperatingImpl implements UserOperatingInte{
    //生成UUID，即主键值
    private String genId;


	/* (non-Javadoc)
	 * <p>Title: modifyUser</p>
	 * <p>Description: 用于修改已有用户的用户信息</p> 
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param userId 待修改信息的用户Id
	 * @param firstName 用户姓氏字段
	 * @param lastName 用户名字字段
	 * @param email 用户邮箱地址
	 * @param password 用户密码
	 * @return user 修改后的用户对象 
	 * @see com.lin.activitiServices.UserGroupServicePackaging.UserService.UserOperatingInte#modifyUser(org.activiti.engine.IdentityService, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)  
	 */
	public User modifyUser(IdentityService identityService, String userId, String firstName, String lastName,
			String email, String password) {
		// TODO Auto-generated method stub
		User user = identityService.createUserQuery().userId(userId).singleResult();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		identityService.saveUser(user);
		
		identityService.saveUser(user);
		return user;
	}
    
	/* (non-Javadoc)
	 * <p>Title: singleCreateUser</p>
	 * <p>Description: 用于创建单个用户</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param firstName 用户姓氏
	 * @param lastName 用户名字
	 * @param email 用户Email地址
	 * @param password 用户密码
	 * @return User 返回一个已注册的用户对象
	 * @see com.lin.activitiServices.UserGroupServicePackaging.UserService.UserOperatingInte#singleCreateUser(org.activiti.engine.IdentityService, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public User singleCreateUser(IdentityService identityService, String firstName, String lastName, String email,
			String password) {
		// TODO Auto-generated method stub
		genId = UUID.randomUUID().toString();
		
		User user = identityService.newUser(genId);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		identityService.saveUser(user);
		
		return user;
	}

	/* (non-Javadoc)
	 * <p>Title: singleDropUser</p>
	 * <p>Description: 用于删除单个用户</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param id 用户的Id
	 * @see com.lin.activitiServices.UserGroupServicePackaging.UserService.UserOperatingInte#singleDropUser(org.activiti.engine.IdentityService, java.lang.String)
	 */
	public void singleDropUser(IdentityService identityService, String userId) {
		// TODO Auto-generated method stub
		identityService.deleteUser(userId);
		
	}

	/* (non-Javadoc)
	 * <p>Title: batchDropUse</p>
	 * <p>Description: 用于批量删除用户</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param ids 用户id的列表
	 * @see com.lin.activitiServices.UserGroupServicePackaging.UserService.UserOperatingInte#batchDropUse(org.activiti.engine.IdentityService, java.util.List)
	 */
	public void batchDropUser(IdentityService identityService, List<String> ids) {
		// TODO Auto-generated method stub
		for(String id : ids) {
			identityService.deleteUser(id);
		}
	}

	/* (non-Javadoc)
	 * <p>Title: allUser</p>
	 * <p>Description: 用于获取所有用户</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @return users 所用已注册的用户对象列表
	 * @see com.lin.activitiServices.UserGroupServicePackaging.UserService.UserOperatingInte#allUser(org.activiti.engine.IdentityService)
	 */
	public List<User> allUser(IdentityService identityService) {
		// TODO Auto-generated method stub
		List<User> users = identityService.createUserQuery().list();
		
		return users;
	}
	
	/* (non-Javadoc)
	 * <p>Title: fuzzyQueryByName</p>
	 * <p>Description: 此方法使用用户姓氏或名字单条件查找或组合条件查找用户组</p> 
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param firstName 待查询的用户姓氏
	 * @param lastName 待查询的用户名字
	 * @return users 返回查询所得的所有用户对象列表 
	 * @see com.lin.activitiServices.UserGroupServicePackaging.UserService.UserOperatingInte#fuzzyQueryByName(org.activiti.engine.IdentityService, java.lang.String, java.lang.String)  
	 */
	public List<User> fuzzyQueryByName(IdentityService identityService, String firstName, String lastName) {
		// TODO Auto-generated method stub
		List<User> users =  new ArrayList<User>();
		
		if (firstName.equals("firstName") != true && lastName.equals("lastName") != true) {
			users = identityService.createUserQuery().userFirstName(firstName).userLastName(lastName).list();
		}else if(firstName.equals("firstName") != true) {
			users = identityService.createUserQuery().userFirstName(firstName).list();
		}else if(lastName.equals("lastName") != true) {
			users = identityService.createUserQuery().userLastName(lastName).list();
		}else {
			users = identityService.createUserQuery().list();
		}
		
		return users;
	}

	/* (non-Javadoc)
	 * <p>Title: checkUserPassword</p>
	 * <p>Description: 用于验证用户密码</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param userId 用户Id
	 * @param password 用户密码
	 * @return check 验证结果
	 * @see com.lin.activitiServices.UserGroupServicePackaging.UserService.UserOperatingInte#checkUserPassword(org.activiti.engine.IdentityService, java.lang.String, java.lang.String)
	 */
	public Boolean checkUserPassword(IdentityService identityService, String userId, String password) {
		// TODO Auto-generated method stub
		Boolean check = identityService.checkPassword(userId, password);
		return check;
	}
	
	/* (non-Javadoc)
	 * <p>Title: allGroups</p>
	 * <p>Description: 用于查询用户组下的所有关联用户</p> 
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param group 待查询的用户组对象
	 * @return users 查询所得的用户组下所有绑定的用户对象列表
	 * @see com.lin.activitiServices.UserGroupServicePackaging.UserService.UserOperatingInte#allGroups(org.activiti.engine.IdentityService, org.activiti.engine.identity.User)  
	 */
	public List<Group> allGroups(IdentityService identityService, String userId) {
		// TODO Auto-generated method stub
		List<Group> groups = identityService.createGroupQuery().groupMember(userId).list();
		return groups;
	}

	/* (non-Javadoc)
	 * <p>Title: getStartableUsers</p>
	 * <p>Description: 此方法用于查找指定流程定义所绑定的所有用户</p> 
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param processDefinitionId 待查询绑定情况的流程定义Id
	 * @return users 返回已绑定该流程定义的所有用户的对象列表 
	 * @see com.lin.activitiServices.UserGroupServicePackaging.UserService.UserOperatingInte#getStartableUsers(org.activiti.engine.IdentityService, java.lang.String)  
	 */
	public List<User> getStartableUsers(IdentityService identityService, String processDefinitionId) {
		// TODO Auto-generated method stub
		List<User> users = identityService.createUserQuery().potentialStarter(processDefinitionId).list();
		return users;
	}
}
