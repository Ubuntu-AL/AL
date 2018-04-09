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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lin.activitiServices.UserGroupServicePackaging.GroupService.GroupOperatingInte;
import com.lin.activitiServices.UserGroupServicePackaging.UserInfoService.UserInfoOperatingInte;
import com.lin.activitiServices.UserGroupServicePackaging.UserService.UserOperatingInte;
import com.lin.webMC.jsonBeans.RequestJson;
import com.lin.webMC.pojo.ResponseBodyPackaging;
/**
 * @author admin
 * <p>Description: 此控制器内，提供管理用户和用户组关联关系的服务方法</p>
 */
@RestController
public class RelationshipController {
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private UserOperatingInte userOperating;
	
	@Autowired
	private UserInfoOperatingInte userInfoOperating;
		
	@Autowired
	private GroupOperatingInte groupOperating;
	
	@Autowired
	private ResponseBodyPackaging responseBodyPackaging;
	
	/******************
	 *    UserPart   * 
	******************/
	
	/**
	 * <p>Title: getUserGroups</p>  
	 * <p>Description: 此服务方法用于获取指定用户在的所有用户组的对象列表</p> 
	 * @param userId 待查询用户组的用户Id
	 * @return response 返回用户所在的所有用户组的对象列表
	 */
	@GetMapping("/relationship/getUserGroups")
	@ResponseBody
	public Map<String, Group> getUserGroups(@RequestParam(value = "userId") String userId){
		
		List<Group> groups = new ArrayList<Group>();
		Map<String, Group> response = new HashMap<String, Group>();
		
		groups = userOperating.allGroups(identityService, userId);
		response = responseBodyPackaging.groupListPackaging(groups);
		
		return response;
	}
	
	/**
	 * <p>Title: accountVerification</p>  
	 * <p>Description: 此服务方法提供用户账号和密码验证的功能</p> 
	 * @param accountName 待验证的用户账号
	 * @param password 待校验账号的用户密码
	 * @return check 返回校验成功与否的Boolean值
	 */
	@GetMapping("/relationship/accountVerification")
	@ResponseBody
	public Boolean accountVerification(@RequestParam(value = "accountName") String accountName,
									   @RequestParam(value = "password") String password) {
		Boolean check = false;
		
		List<User> users = userOperating.allUser(identityService);
		try {	
			User user = userInfoOperating.checkUserAccountName(identityService, accountName, users);
			check = userOperating.checkUserPassword(identityService, user.getId(), password);
			
			return check;
		}catch (NullPointerException ne) {
			// TODO: handle exception
			return check;
		}
	}
	
	
	/******************
	 *   GroupPart   * 
	******************/
	
	/**
	 * <p>Title: getGroupUsers</p>  
	 * <p>Description: 此服务方法用于获取指定用户组下所有用户的对象列表</p> 
	 * @param groupId 待查询用户的用户组Id
	 * @return response 返回用户组下所有用户的对象列表
	 */
	@GetMapping("/relationship/getGroupUsers")
	@ResponseBody
	public Map<String, User> getGroupUsers(@RequestParam(value = "groupId") String groupId){
	
		List<User> users = new ArrayList<User>();
		Map<String, User> response = new HashMap<String ,User>();
		
		users = groupOperating.allMembers(identityService, groupId);
		response = responseBodyPackaging.userListPackaging(users);
		
		return response;
	}
	
	/**
	 * <p>Title: bindingUsers</p>  
	 * <p>Description: 此服务方法为用户组提供用户绑定功能，可批量绑定</p> 
	 * @param requestJson 接收请求json请求数据，内含groupId字符串和一个userIds字符串列表
	 * @return response 绑定用户后的，现用户组所有绑定用户的对象列表
	 */
	@PostMapping("/relationship/bindingUsers")
	@ResponseBody
	public Map<String, User> bindingUsers(@RequestBody RequestJson requestJson){
		
		List<User> users = new ArrayList<User>();
		Map<String, User> response = new HashMap<String ,User>();
		
		for(String userId : requestJson.getUserIds()) {
			groupOperating.createMemberShip(identityService, userId, requestJson.getGroupId());
		}
		users = groupOperating.allMembers(identityService, requestJson.getGroupId());
		response = responseBodyPackaging.userListPackaging(users);
		
		return response;
	}
	
	/**
	 * <p>Title: unbindingUsers</p>  
	 * <p>Description: 此服务方法为用户组提供用户解绑功能，可批量解绑</p> 
	 * @param requestJson 接收请求json请求数据，内含groupId字符串和一个userIds字符串列表
	 * @return response 解绑用户后的，现用户组所有绑定用户的对象列表
	 */
	@PostMapping("/relationship/unbindingUsers")
	@ResponseBody
	public Map<String, User> unbindingUsers(@RequestBody RequestJson requestJson){
		
		List<User> users = new ArrayList<User>();
		Map<String, User> response = new HashMap<String ,User>();
		
		for(String userId : requestJson.getUserIds()) {
			groupOperating.dropMemberShip(identityService, userId, requestJson.getGroupId());
		}
		users = groupOperating.allMembers(identityService, requestJson.getGroupId());
		response = responseBodyPackaging.userListPackaging(users);
		
		return response;
	}
}
