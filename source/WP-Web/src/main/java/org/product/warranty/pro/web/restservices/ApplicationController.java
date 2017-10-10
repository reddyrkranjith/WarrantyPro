package org.product.warranty.pro.web.restservices;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.product.warranty.pro.services.handlers.WPServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplicationController {
	
	Logger LOG_R = Logger.getLogger(ApplicationController.class);
	
	@RequestMapping(value = {"/index"}, method = RequestMethod.GET)
	public String home() throws WPServiceException {
		return "home";
	}
	
	
	@RequestMapping(value = {"/getUsers"}, method = RequestMethod.GET)
	public @ResponseBody Object getUsers() throws WPServiceException {
		User user = new User();
		user.setId(341L);
		user.setName("John Andy");
		user.setKey(UUID.randomUUID().toString());
		return user;
	}
	
	class User {
		private Long id;
		private String name;
		private String key;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
	}
}
