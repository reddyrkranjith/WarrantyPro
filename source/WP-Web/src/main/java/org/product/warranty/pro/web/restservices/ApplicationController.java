package org.product.warranty.pro.web.restservices;

import org.apache.log4j.Logger;
import org.product.warranty.pro.services.handlers.WPServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApplicationController {
	
	Logger LOG_R = Logger.getLogger(ApplicationController.class);
	
	@RequestMapping(value = {"/index"}, method = RequestMethod.GET)
	public String home() throws WPServiceException {
		return "home";
	}
	
	
}
