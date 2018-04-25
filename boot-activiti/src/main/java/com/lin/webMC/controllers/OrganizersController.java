/**
 * <p>Description: 控制器类</p> 
 */
package com.lin.webMC.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lin.activitiServices.UserGroupServicePackaging.GroupService.GroupOperatingInte;
import com.lin.activitiServices.UserGroupServicePackaging.UserInfoService.UserInfoOperatingInte;
import com.lin.activitiServices.UserGroupServicePackaging.UserService.UserOperatingInte;
import com.lin.webMC.jsonBeans.ResponseGroup;
import com.lin.webMC.jsonBeans.ResponseUser;
import com.lin.webMC.pojo.ResponseBodyPackaging;

/**
 * @author Lin
 * <p>Description: 此控制器内，提供用户、用户信息和用户组的增删改查服务方法</p> 
 */
@RestController
public class OrganizersController {

	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private UserOperatingInte userOperating;
		
	@Autowired
	private GroupOperatingInte groupOperating;
	
	@Autowired
	private UserInfoOperatingInte userInfoOperating;
	
	@Autowired
	private ResponseBodyPackaging responseBodyPackaging;
		
	/******************
	 * UserOperating * 
	******************/
	
	/**
	 * <p>Title: allUsers</p>  
	 * <p>Description: 此服务方法提供查询所有用户的服务</p> 
	 * @return responseUsers 返回现有所有用户及信息的对象列表
	 */
	@GetMapping("/organization/userList")
	public List<Map<String, ResponseUser>> allUsers(){
		
		List<Map<String, ResponseUser>> responseUsers = new ArrayList<Map<String , ResponseUser>>();
		List<User> users = new ArrayList<User>();
		
		users = userOperating.allUser(identityService);
		for(User usr: users) {
			ResponseUser responseUser = new ResponseUser();
			responseUser.setName(usr.getFirstName()+usr.getLastName());
			responseUser.setUser(usr);
			responseUser.setUserInfo(userInfoOperating.getDefaultInfos(identityService, usr.getId()));
			Map<String, ResponseUser> usermap = new HashMap<String, ResponseUser>();
			usermap.put("usermsg", responseUser);
			responseUsers.add(usermap);
		}
		
		return responseUsers;
	}
	
	/**
	 * <p>Title: userFuzzyQuery</p>  
	 * <p>Description: 此服务方法提供使用用户姓氏和名字进行但条件查找或者组合条件查找的功能</p> 
	 * @param firstName 待查询的用户姓氏
	 * @param lastName 待查询的用户名字
	 * @return responseUsers 返回现有所有用户及信息的对象列表
	 */
	@GetMapping("/organization/userFuzzyQuery")
	@ResponseBody
	public Map<String,ResponseUser> userFuzzyQuery(@RequestParam(defaultValue = "firstName") String firstName, 
												   @RequestParam(defaultValue = "lastName") String lastName){
		
		Map<String, ResponseUser> responseUsers = new HashMap<String ,ResponseUser>();
		List<User> users = new ArrayList<User>();
		int i = 1;
		System.out.println(firstName+lastName);
		
		users = userOperating.fuzzyQueryByName(identityService, firstName, lastName);
		for(User usr: users) {
			ResponseUser responseUser = new ResponseUser();
			responseUser.setUser(usr);
			responseUser.setUserInfo(userInfoOperating.getDefaultInfos(identityService, usr.getId()));
			responseUsers.put("usermsg"+i, responseUser);
			
			i ++;
		}
		
		return responseUsers;
	}
	
	/**
	 * <p>Title: setUser</p>  
	 * <p>Description: 此服务方法提供新增单个用户和用户信息的功能,同时可作为修改已有用户和用户信息的服务方法</p>  
	 * @param userId 待修改信息的用户Id
	 * @param firstName 用户姓氏
	 * @param lastName 用户名字
	 * @param sex 用户性别
	 * @param phone 用户手机号
	 * @param email 用户邮箱地址
	 * @param loginName 用户登陆系统时所需要输入的登陆名
	 * @param password 用户密码
	 * @return responseUsers 返回新增用户后，现有所有用户及信息的对象列表
	 */
	@PostMapping("/organization/setUser")
	@ResponseBody
	public Map<String,ResponseUser> setUser(@RequestParam(defaultValue = "new") String userId,
											@RequestParam(value = "firstName") String firstName, 
							  				@RequestParam(value = "lastName") String lastName,
							  				@RequestParam(defaultValue= "男") String sex,
							  				@RequestParam(defaultValue= "暂无") String phone,
							  				@RequestParam(value = "email") String email,
							  				@RequestParam(defaultValue = "loginName") String loginName,
							  				@RequestParam(value = "password") String password) {
		
		Map<String, ResponseUser> responseUsers = new HashMap<String ,ResponseUser>();
		List<User> users = new ArrayList<User>();
		Map<String, String> information = new HashMap<String, String>();
		
		information.put("sex", sex);
		information.put("phone", phone);
		
		if(loginName.equals("loginName")) {
			information.put("loginName", firstName+lastName);
		}else {
			information.put("loginName", loginName);
		}
		
		if(userId.equals("new")) {
			User user = userOperating.singleCreateUser(identityService, firstName, lastName, email, password);
			userInfoOperating.setUserInfo(identityService, user, information);
		}else {
			User user = userOperating.modifyUser(identityService, userId, firstName, lastName, email, password);
			userInfoOperating.setUserInfo(identityService, user, information);
		}
		
		users = userOperating.allUser(identityService);
		for(User usr: users) {
			ResponseUser responseUser = new ResponseUser();
			responseUser.setUser(usr);
			responseUser.setUserInfo(userInfoOperating.getDefaultInfos(identityService, usr.getId()));
			
		}
		
		return responseUsers;
	}
	
	/**
	 * <p>Title: deletUser</p>  
	 * <p>Description: 此服务方法提供删除单个用户的功能</p> 
	 * @param userId 待删除的用户Id
	 * @return responseUsers 返回删除用户后，现有所有用户及信息的对象列表
	 */
	@GetMapping("/organization/deletUser")
	@ResponseBody
	public Map<String,ResponseUser> deletUser(@RequestParam("userId") String userId){
		
		Map<String, ResponseUser> responseUsers = new HashMap<String ,ResponseUser>();
		List<User> users = new ArrayList<User>();
		int i = 1;
		
		userOperating.singleDropUser(identityService, userId);
		users = userOperating.allUser(identityService);
		for(User usr: users) {
			ResponseUser responseUser = new ResponseUser();
			responseUser.setUser(usr);
			responseUser.setUserInfo(userInfoOperating.getDefaultInfos(identityService, usr.getId()));
			responseUsers.put("usermsg"+i, responseUser);
			
			i ++;
		}
		
		return responseUsers;
	}
	
	/**
	 * <p>Title: deletUsers</p>  
	 * <p>Description: 此服务方法提供批量删除用户的功能</p> 
	 * @param userids 待删除的用户的id列表
	 * @return responseUsers 返回批量删除用户后，现有所有用户及信息的对象列表
	 */
	@PostMapping("/organization/deletUsers")
	@ResponseBody
	public Map<String, ResponseUser> deletUsers(@RequestParam("userIds") List<String> userids){
		
		Map<String, ResponseUser> responseUsers = new HashMap<String, ResponseUser>();
		List<User> users = new ArrayList<User>();
		
		userOperating.batchDropUser(identityService, userids);
		users = userOperating.allUser(identityService);
		for(User usr: users) {
			ResponseUser responseUser = new ResponseUser();
			responseUser.setUser(usr);
			responseUser.setUserInfo(userInfoOperating.getDefaultInfos(identityService, usr.getId()));
		}
		
		return responseUsers;
	}
	
	/*******************
	 * GroupOperating *
	*******************/
	
	/**
	 * <p>Title: allGroups</p>  
	 * <p>Description: 此服务方法提供查询所有用户组的服务</p> 
	 * @return response 返回现有所有用户组对象的列表
	 */
	@GetMapping("/organization/groupList")
	@ResponseBody
	public List<Map<String, ResponseGroup>> allGroups(){
		List<Map<String, ResponseGroup>> responseGroups = new ArrayList<Map<String, ResponseGroup>>();
		List<Group> groups = new ArrayList<Group>();
		
		groups = groupOperating.allGroups(identityService);
		for(Group group: groups) {
			ResponseGroup responseGroup = new ResponseGroup();
			responseGroup.setGroup(group);
			responseGroup.setName(group.getName());
			Map<String, ResponseGroup> groupMap = new HashMap<String, ResponseGroup>();
			groupMap.put("groupmsg", responseGroup);
			responseGroups.add(groupMap);
		
		}
		
		return responseGroups;
	}
	
	/**
	 * <p>Title: groupFuzzyQuery</p>  
	 * <p>Description: 此服务方法提供使用用户组名进行模糊查找的功能</p> 
	 * @param groupName 待模糊查询的用户组名
	 * @return response 返回现有所有用户组对象的列表
	 */
	@GetMapping("/organization/groupFuzzyQuery")
	public Map<String, Group> groupFuzzyQuery(@RequestParam(value = "groupName") String groupName){
		
		List<Group> groups = new ArrayList<Group>();
		Map<String, Group> response = new HashMap<String, Group>();
		
		groups = groupOperating.fuzzyQueryByName(identityService, groupName);
		response = responseBodyPackaging.groupListPackaging(groups);
		
		return response;
	}
	

	/**
	 * <p>Title: setGroup</p>  
	 * <p>Description: 此服务方法提供单个用户组的创建功能,同时可作为修改已有用户组信息的服务方法</p> 
	 * @param groupName 用户组的名称
	 * @param type 用户组的类型
	 * @return response 返回创建用户组后，现有所有用户组对象的列表
	 */
	@PostMapping("/organization/setGroup")
	@ResponseBody
	public Map<String, Group> setGroup(@RequestParam(defaultValue = "new") String groupId,
									   @RequestParam(value = "groupName") String groupName, 
									   @RequestParam(value = "type") String type){
		
		List<Group> groups = new ArrayList<Group>();
		Map<String, Group> response = new HashMap<String, Group>();
		
		if(groupId.equals("new")) {
			groupOperating.singleCreateGroup(identityService, groupName, type);
		}else {
			groupOperating.modifyGroup(identityService, groupId, groupName, type);
		}
		
		groups = groupOperating.allGroups(identityService);
		response = responseBodyPackaging.groupListPackaging(groups);
		
		return response;
	}
	

	/**
	 * <p>Title: deletGroup</p>  
	 * <p>Description: 此服务方法提供删除单个用户组的功能</p> 
	 * @param groupId 待删除的用户组的Id
	 * @return response 返回删除用户组后，现有所有用户组对象的列表
	 */
	@GetMapping("/organization/deletGroup")
	@ResponseBody
	public Map<String, Group> deletGroup(@RequestParam("groupId") String groupId){
		
		List<Group> groups = new ArrayList<Group>();
		Map<String, Group> response = new HashMap<String, Group>();
		
		groupOperating.singleDropGroup(identityService, groupId);
		response = responseBodyPackaging.groupListPackaging(groups);
		
		return response;
	}
	

	/**
	 * <p>Title: deletGroups</p>  
	 * <p>Description: 此服务方法提供批量删除用户组的功能</p> 
	 * @param groupIds 待删除的用户组的Id列表
	 * @return response 返回批量删除用户组后，现有所有用户组对象的列表
	 */
	@PostMapping("/organization/deletGroups")
	@ResponseBody
	public Map<String, Group> deletGroups(@RequestParam("groupIds") List<String> groupIds){
		
		List<Group> groups = new ArrayList<Group>();
		Map<String, Group> response = new HashMap<String, Group>();
		
		groupOperating.batchDropGroup(identityService, groupIds);
		response = responseBodyPackaging.groupListPackaging(groups);
		
		return response;
	}
}
