/**
 * 
 */
package com.lin.webMC.controllers;

import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author admin
 *
 */
@RestController
public class FirstController {
	
	@Autowired
	RepositoryService repositiryService;
	
	@GetMapping("/hello")
	@ResponseBody
	public void welcom() {
		
		System.out.println(repositiryService.createDeploymentQuery().count());
		
		
	}
}
