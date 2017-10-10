package org.product.warranty.pro.web.restservices;

import org.json.JSONObject;
import org.product.warranty.pro.services.handlers.WPServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/warranty/api/v0.1/")
public class RestAPIController {
	
	@RequestMapping(value = {"/getAdmins"}, method = RequestMethod.GET)
	public @ResponseBody String getAdmins() throws WPServiceException {
		return JSONObject.quote("Hello World");
	}
}
