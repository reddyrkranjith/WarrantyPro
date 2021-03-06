package org.product.warranty.pro.repository;

import java.util.List;

import org.product.warranty.pro.entities.ProUser;
import org.product.warranty.pro.repository.exception.WPDataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public interface ProUserRepository {
	
	public void createProUser(ProUser user) throws WPDataAccessException;
	
	public ProUser getUserByUsernameOrEmail(String email) throws WPDataAccessException;
	
	public List<ProUser> getAllUsers() throws WPDataAccessException;

	public ProUser getUserByEmail(String email) throws WPDataAccessException;

	public void updateUserAuthToken(ProUser entity) throws WPDataAccessException;

}
