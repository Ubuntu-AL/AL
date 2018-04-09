/**
 * <p>Description: 方法类</p>
 */
package com.lin.webMC.pojo;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * <p>Description: 此类用于将List类型的返回值转存至Map对象中，以便json格式返回</p>
 */
@Service
public class ResponseBodyPackaging {
	
	public Map<String, User> userListPackaging(List<User> users){
		Map<String, User> responses = new HashMap<String, User>();
		
		for(User user : users) {
			responses.put(user.getId(), user);
		}
		
		return responses;
	}

	public Map<String, Group> groupListPackaging(List<Group> groups){
		Map<String, Group> responses = new HashMap<String, Group>();
		
		for(Group group : groups) {
			responses.put(group.getId(), group);
		}
		
		return responses;
	}
	
	public Object listPackaging(List<?> list){
		Map<String, Object> response = new HashMap<String, Object>();
		Integer i = 1;
		
		for(Object object : list) {
			response.put(i.toString(), object);
			i++;
		}
		
		return response;
	}
}
