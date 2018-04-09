/**
 * 
 */
package com.lin.activitiServices.UserGroupServicePackaging.UserInfoService;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.springframework.stereotype.Service;

/**
 * <p>Title: UserInfoOperatingImpl</p>  
 * <p>Description: 此类用于实现用户信息操作方法</p>  
 * @author Lin 
 * @date 2018年3月17日 
 */
@Service("userInfoOperatingInte")
public class UserInfoOperatingImpl implements UserInfoOperatingInte{

	/* (non-Javadoc)
	 * <p>Title: setUserInfo</p>
	 * <p>Description: 用于批量或者单个用户Info数据的设置</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param user 待设置info数据的用户对象
	 * @param information 待加入info数据表的字段和值，为Map类型：Key为字段名，value为字段值
	 * @see com.lin.activitiServices.UserGroupServicePackaging.UserInfoService.UserInfoOperatingInte#setUserInfo(org.activiti.engine.IdentityService, org.activiti.engine.identity.User, java.util.Map)
	 */
	public void setUserInfo(IdentityService identityService, User user, Map<String, String> information) {
		// TODO Auto-generated method stub
		for(String key : information.keySet()) {
			identityService.setUserInfo(user.getId(), key, information.get(key));
		}
	}
	
	/* (non-Javadoc)
	 * <p>Title: setPicture</p>
	 * <p>Description: 用于设置用户图片</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param fis 待加入picture表的图片输入源
	 * @see com.lin.activitiServices.UserGroupServicePackaging.UserInfoService.UserInfoOperatingInte#setPicture(org.activiti.engine.IdentityService, java.io.FileInputStream)
	 */
	public void setPicture(IdentityService identityService, FileInputStream fis, User user) {
		// TODO Auto-generated method stub
		try {
			BufferedImage image = ImageIO.read(fis);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ImageIO.write(image, "png", output);
			byte[] picArray = output.toByteArray();
			Picture picture = new Picture(picArray, "User image");
			identityService.setUserPicture(user.getId(), picture);
		}catch (IOException ioe) {
			// TODO: handle exception
			System.out.println("IO ERROR!");
		}
	}

	/* (non-Javadoc)
	 * <p>Title: undoUserInfo</p>
	 * <p>Description: 用于批量或者单个撤销已设置的用户Info字段数据</p>
	 * @param identityService 为保证服务类的单例性，从业务函数获取服务实例
	 * @param user 待设置Info数据的用户对象
	 * @param fields 待撤销的Info数据表字段名列表
	 * @see com.lin.activitiServices.UserGroupServicePackaging.UserInfoService.UserInfoOperatingInte#undoUserInfo(org.activiti.engine.IdentityService, org.activiti.engine.identity.User, java.util.List)
	 */
	public void undoUserInfo(IdentityService identityService, User user, List<String> fields) {
		// TODO Auto-generated method stub
		for(String field : fields) {
			identityService.deleteUserInfo(user.getId(), field);
		}
	}

	/* (non-Javadoc)
	 * <p>Title: checkUserAccountName</p>  
	 * <p>Description: 用于查找账户名，确定用户对象，为用户验证提供服务</p> 
	 * @param indexOutOfBoundsException 为保证服务类的单例性，从业务函数获取服务实例
	 * @param users 所有用户对象的列表
	 * @return user 确认需要进行用户验证的用户对象,若没找到对应账户的用户对象说明用户不存在
	 * @see com.lin.activitiServices.UserGroupServicePackaging.UserInfoService.UserInfoOperatingInte#checkUserAccountName(java.lang.IndexOutOfBoundsException, java.util.List)  
	 */
	public User checkUserAccountName(IdentityService identityService, String accountName, List<User> users) {
		// TODO Auto-generated method stub
		for(User user : users) {
			if(identityService.getUserInfo(user.getId(), "loginName").equals(accountName)){
				return user;
			}
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * <p>Title: getDefaultInfos</p>
	 * <p>Description: 用于反馈用户默认信息的数据字典</p> 
	 * @param userId 待查询用户信息的用户Id
	 * @return defaultInfos 用户默认设置的用户信息字典
	 * @see com.lin.activitiServices.UserGroupServicePackaging.UserInfoService.UserInfoOperatingInte#getDefaultInfos(java.lang.String)  
	 */
	public Map<String, String> getDefaultInfos(IdentityService identityService, String userId) {
		// TODO Auto-generated method stub
		Map<String, String> defaultIfos = new HashMap<String, String>();
		
		defaultIfos.put("sex", identityService.getUserInfo(userId, "sex"));
		defaultIfos.put("phone", identityService.getUserInfo(userId, "phone"));
		defaultIfos.put("loginName", identityService.getUserInfo(userId, "loginName"));
		
		return defaultIfos;
	}

}
