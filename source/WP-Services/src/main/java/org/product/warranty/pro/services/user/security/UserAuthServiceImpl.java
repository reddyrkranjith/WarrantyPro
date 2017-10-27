package org.product.warranty.pro.services.user.security;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.product.warranty.pro.entities.ProUser;
import org.product.warranty.pro.repository.ProUserRepository;
import org.product.warranty.pro.repository.exception.WPDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserAuthService")
public class UserAuthServiceImpl implements UserDetailsService {

	protected static final Logger LOG_R = Logger.getLogger(UserAuthServiceImpl.class);
	
	@Autowired
	@Qualifier("ProUserRepository")
	private ProUserRepository repo;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException {
		UserDetails user = null;
		try {
			ProUser loadUserByUserName = repo.getUserByEmail(email);
			if (loadUserByUserName != null) {
				Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
				GrantedAuthority grant = new GrantedAuthorityImpl("ROLE_USER");
				authorities.add(grant);
				user = new UserDetailsImpl(loadUserByUserName.getEmail(),loadUserByUserName.getPassword(), loadUserByUserName.getUsername(), authorities);
			} else {
				throw new UsernameNotFoundException("User name not found.");
			}
		} catch (WPDataAccessException e) {
			throw new UsernameNotFoundException("Exception occurred wile reading user info."); 
		}
		return user;
	}
}