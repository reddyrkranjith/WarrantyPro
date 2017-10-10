package org.product.warranty.pro.services.user.security;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserAuthService")
public class UserAuthServiceImpl implements UserDetailsService {

	protected static final Logger LOG_R = Logger.getLogger(UserAuthServiceImpl.class);

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException {
		UserDetails user = null;
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		GrantedAuthority grant = new GrantedAuthorityImpl("ROLE_ADMIN");
		authorities.add(grant);
		user = new UserDetailsImpl("reddy.rkranjith@gmail.com", "12345678", "RanjithReddy", authorities);
		return user;
	}
}