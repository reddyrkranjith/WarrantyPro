package org.product.warranty.pro.web.restservices;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.product.warranty.pro.beans.request.ProUserLoginRequestBean;
import org.product.warranty.pro.beans.request.ProUserRequestBean;
import org.product.warranty.pro.beans.response.ProUserResponeBean;
import org.product.warranty.pro.services.ProUserServices;
import org.product.warranty.pro.services.handlers.WPServiceException;
import org.product.warranty.pro.web.restservices.errorhandler.InvalidRequestException;
import org.product.warranty.pro.web.restservices.errorhandler.SuccessResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/warranty/api/user")
public class RestAPIController {
	
	Logger LOG_R = Logger.getLogger(RestAPIController.class);
	static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
	
	@Autowired
	@Qualifier("ProUserServices")
	ProUserServices proUserServices;
	
	@RequestMapping(value = {"/createUser"}, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Object createNewProUser(@Valid @RequestBody ProUserRequestBean userBean, BindingResult bindingResult) 
			throws WPServiceException {
		if(bindingResult.hasErrors()) {
			throw new InvalidRequestException("Invalid Request", bindingResult);
		} else {
			proUserServices.createProUser(userBean);
			SuccessResource resource = new SuccessResource("Success", "User created successfully.");
			return resource;
		}
	}
	
	@RequestMapping(value = {"/getUsers"}, method = RequestMethod.GET)
	public @ResponseBody Object getUsers() throws WPServiceException {
		List<ProUserResponeBean> users = proUserServices.getAllUsers();
		return users;
	}
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public @ResponseBody Object login(@RequestBody ProUserLoginRequestBean proUserLoginRequestBean, 
			BindingResult bindingResult) throws WPServiceException {
		String jwtToken = "";
		if(bindingResult.hasErrors()) {
			throw new InvalidRequestException("Invalid Request", bindingResult);
		} else {
			ProUserResponeBean user = proUserServices.loginWithUsernameOrEmail(proUserLoginRequestBean);
			jwtToken = Jwts.builder().setSubject(user.getEmail()).claim("roles", "ROLE_USER").setIssuedAt(new Date())
		            .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
			user.setToken(jwtToken);
			proUserServices.updateUserAuthToken(user);
			return user;
		}
	}
	
	@RequestMapping(value = {"/getAdmins"}, method = RequestMethod.GET)
	public @ResponseBody Object getAdmins() throws WPServiceException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("UserDetails : "+authentication.getName());
		List<ProUserResponeBean> users = proUserServices.getAllUsers();
		return users;
	}
}
