package org.product.warranty.pro.services.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.product.warranty.pro.beans.request.ProUserRequestBean;
import org.product.warranty.pro.beans.response.ProUserResponeBean;
import org.product.warranty.pro.entities.ProUser;
import org.product.warranty.pro.repository.ProUserRepository;
import org.product.warranty.pro.repository.exception.WPDataAccessException;
import org.product.warranty.pro.services.ProUserServices;
import org.product.warranty.pro.services.handlers.WPServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ProUserServices")
public class ProUserServicesImpl implements ProUserServices{
	
	@Autowired
	@Qualifier("ProUserRepository")
	ProUserRepository proUserRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(rollbackFor = WPServiceException.class)
	public void createProUser(ProUserRequestBean user) throws WPServiceException {
		try {
			proUserRepository.createProUser(createProUserEntity(user));
		} catch (WPDataAccessException e) {
			throw new WPServiceException(e.getMessage(), 500);
		}
	}
	
	private ProUser createProUserEntity(ProUserRequestBean user) {
		ProUser userEntity = new ProUser();
		userEntity.setUserkey(UUID.randomUUID().toString());
		userEntity.setUsername(user.getUsername());
		userEntity.setEmail(user.getEmail());
		userEntity.setMobile(user.getMobile());
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
		userEntity.setCreatedDate(new Date());
		userEntity.setUpdatedDate(new Date());
		return userEntity;
	}

	@Override
	public List<ProUserResponeBean> getAllUsers() throws WPServiceException {
		List<ProUserResponeBean> users = new ArrayList<>();
		try {
			List<ProUser> entities = proUserRepository.getAllUsers();
			for(ProUser entity : entities) {
				ProUserResponeBean user = new ProUserResponeBean();
				user.setId(entity.getId());
				user.setUserkey(entity.getUserkey());
				user.setUsername(entity.getUsername());
				user.setEmail(entity.getEmail());
				user.setMobile(entity.getMobile());
				user.setCreatedDate(entity.getCreatedDate());
				user.setUpdatedDate(entity.getUpdatedDate());
				users.add(user);
			}
		} catch (WPDataAccessException e) {
			throw new WPServiceException(e.getMessage(), 500);
		}
		return users;
	}
}
