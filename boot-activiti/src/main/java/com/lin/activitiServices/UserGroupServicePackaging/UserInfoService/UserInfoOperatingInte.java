/**
 * 
 */
package com.lin.activitiServices.UserGroupServicePackaging.UserInfoService;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;

/**
 * <p>Title: UserInfoOperatingInte</p>  
 * <p>Description: 此类用于定义用户信息操作方法</p>  
 * @author Lin 
 * @date 2018年3月17日 
 */
public interface UserInfoOperatingInte {
	/**
	 * <p>Title: setUserInfo</p>
	 * <p>Description: 用于设置用户Info数据</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param user 待设置info数据的用户对象
	 * @param information 待加入info数据表的字段和值，为Map类型：Key为字段名，value为字段值
	 */
	public void setUserInfo(IdentityService identityService, User user, Map<String, String> information);
	
	/**
	 * <p>Title: setPicture</p>
	 * <p>Description: 用于设置用户图片</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param fis 待加入picture表的图片输入源
	 */
	public void setPicture(IdentityService identityService, FileInputStream fis, User user);
	
	/**
	 * <p>Title: undoUserInfo</p>
	 * <p>Description: 用于撤销已设置的用户Info字段数据</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param user 待设置Info数据的用户对象
	 * @param fields 待撤销的Info数据表字段名列表
	 */
	public void undoUserInfo(IdentityService identityService, User user, List<String> fields);
	
	/**
	 * <p>Title: checkUserAccountName</p>  
	 * <p>Description: 用于查找账户名，确定用户对象，为用户验证提供服务</p> 
	 * @param indentityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param accountName 用户的账户名
	 * @param users 所有用户对象的列表
	 * @return user 确认需要进行用户验证的用户对象
	 */
	public User checkUserAccountName(IdentityService identityService, String accountName, List<User> users);
	
	/**
	 * <p>Title: getDefaultInfos</p>  
	 * <p>Description: 用于反馈用户默认信息的数据字典</p> 
	 * @param userId 待查询用户信息的用户Id
	 * @return defaultInfos 用户默认设置的用户信息字典
	 */
	public Map<String, String> getDefaultInfos(IdentityService identityService, String userId);

}
