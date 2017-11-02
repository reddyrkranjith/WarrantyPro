package org.product.warranty.pro.services.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.product.warranty.pro.beans.request.ProUserLoginRequestBean;
import org.product.warranty.pro.beans.request.ProUserRequestBean;
import org.product.warranty.pro.beans.response.ProUserResponeBean;
import org.product.warranty.pro.entities.ProUser;
import org.product.warranty.pro.repository.ProUserRepository;
import org.product.warranty.pro.repository.exception.WPDataAccessException;
import org.product.warranty.pro.services.ProUserServices;
import org.product.warranty.pro.services.handlers.WPServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("ProUserServices")
public class ProUserServicesImpl implements ProUserServices{
	
	protected static final Logger LOG_R = Logger.getLogger(ProUserServicesImpl.class);
	
	@Autowired
	@Qualifier("ProUserRepository")
	ProUserRepository proUserRepository;
	
	@Autowired
	@Qualifier("encoder")
	private PasswordEncoder encoder;
	
	@Override
	@Transactional(rollbackFor = WPServiceException.class)
	public void createProUser(ProUserRequestBean user) throws WPServiceException {
		try {
			proUserRepository.createProUser(createProUserEntity(user));
		} catch (WPDataAccessException e) {
			throw new WPServiceException(e.getMessage(), 500);
		}
	}
	
	/**
	 * Create {@link ProUser} from {@link ProUserRequestBean} bean
	 * @param userBean - {@link ProUserRequestBean} bean whose property is to be extracted to {@link ProUser}
	 * @return {@link ProUser}
	 */
	private ProUser createProUserEntity(ProUserRequestBean userBean) {
		ProUser userEntity = new ProUser();
		userEntity.setUserkey(UUID.randomUUID().toString());
		userEntity.setUsername(userBean.getUsername());
		userEntity.setEmail(userBean.getEmail());
		userEntity.setMobile(userBean.getMobile());
		userEntity.setPassword(encoder.encode(userBean.getPassword()));
		userEntity.setCreatedDate(new Date());
		userEntity.setUpdatedDate(new Date());
		return userEntity;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ProUserResponeBean loginWithUsernameOrEmail(ProUserLoginRequestBean proUserLoginRequestBean)
			throws WPServiceException {
		try{
			ProUser userEntity = proUserRepository.getUserByUsernameOrEmail(proUserLoginRequestBean.getEmail());
			if(userEntity != null && encoder.matches(proUserLoginRequestBean.getPassword(), userEntity.getPassword())) {
				return createProUserResponseBean(userEntity);
			} else {
				throw new BadCredentialsException("Invalid user credentials.", 400);
			}
		} catch (WPDataAccessException e) {
			throw new WPServiceException(e.getMessage(), 500);
		}
	}
	
	@Override
	public List<ProUserResponeBean> getAllUsers() throws WPServiceException {
		List<ProUserResponeBean> users = new ArrayList<>();
		try {
			List<ProUser> entities = proUserRepository.getAllUsers();
			for(ProUser entity : entities) {
				users.add(createProUserResponseBean(entity));
			}
		} catch (WPDataAccessException e) {
			throw new WPServiceException(e.getMessage(), 500);
		}
		return users;
	}
	
	/**
	 * Create {@link ProUserResponeBean} from {@link ProUser} entity
	 * @param entity - {@link ProUser} Entity whose property is to be extracted to {@link ProUserResponeBean}
	 * @return {@link ProUserResponeBean}
	 */
	private ProUserResponeBean createProUserResponseBean(ProUser entity) {
		ProUserResponeBean user = new ProUserResponeBean();
		user.setId(entity.getId());
		user.setUserkey(entity.getUserkey());
		user.setUsername(entity.getUsername());
		user.setEmail(entity.getEmail());
		user.setMobile(entity.getMobile());
		user.setCreatedDate(entity.getCreatedDate());
		user.setUpdatedDate(entity.getUpdatedDate());
		return user;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = WPServiceException.class)
	public void updateUserAuthToken(ProUserResponeBean user) throws WPServiceException {
		try{
			ProUser entity = proUserRepository.getUserByEmail(user.getEmail());
			if(entity == null){
				throw new WPServiceException("Invalid user", 500);
			}
			entity.setAuth_token(user.getToken());
			proUserRepository.updateUserAuthToken(entity);
		} catch (WPDataAccessException e) {
			throw new WPServiceException(e.getMessage(), 500);
		}
	}
}
