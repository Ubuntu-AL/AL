/**
 * <p>Description: </p>
 */
package com.lin.webMC.pojo;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author admin
 * <p>Description: </p>
 */
@Service
public class ListsMerge {
	 
	public Object operating(List<?> lists) {
		List<Object> response = new ArrayList<Object>();
		
		Iterator<?> it = lists.iterator();
		response.addAll((ArrayList<?>)it.next());
		
		while(it.hasNext()) {
			@SuppressWarnings("unchecked")
			List<Object> temporary = (ArrayList<Object>)it.next();
			temporary.removeAll(response);
			response.addAll(temporary);
		}
		
		return response;
	}
}
