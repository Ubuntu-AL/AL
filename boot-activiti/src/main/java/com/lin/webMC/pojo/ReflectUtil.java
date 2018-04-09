/**
 * <p>Description: </p>
 */
package com.lin.webMC.pojo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @author admin
 * <p>Description: </p>
 */
@Service
public class ReflectUtil {
	
	public Map<String, Object> reflect(Object object){
		
		Map<String, Object> resoult = new HashMap<String, Object>();
		//获取参数类
		Class<? extends Object> cls = object.getClass();
		//将参数类转换为对应属性数量的Field类型数组（即该类有多少个属性字段 N 转换后的数组长度即为 N）
		Field[] fields = cls.getDeclaredFields();
		for(int i = 0;i < fields.length; i ++){
			Field f = fields[i];
			f.setAccessible(true);
			try {
				//f.getName()得到对应字段的属性名，f.get(o)得到对应字段属性值,f.getGenericType()得到对应字段的类型
				resoult.put(f.getName(),f.get(object));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resoult;
	}
}
