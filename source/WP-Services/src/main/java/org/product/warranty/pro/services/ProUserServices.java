package org.product.warranty.pro.services;

import java.util.List;

import org.product.warranty.pro.beans.request.ProUserLoginRequestBean;
import org.product.warranty.pro.beans.request.ProUserRequestBean;
import org.product.warranty.pro.beans.response.ProUserResponeBean;
import org.product.warranty.pro.services.handlers.WPServiceException;
import org.springframework.stereotype.Service;

@Service
public interface ProUserServices {
	
	public void createProUser(ProUserRequestBean user) throws WPServiceException;
	
	public ProUserResponeBean loginWithUsernameOrEmail(
			ProUserLoginRequestBean proUserLoginRequestBean) throws WPServiceException;
	
	public List<ProUserResponeBean> getAllUsers() throws WPServiceException;
}
