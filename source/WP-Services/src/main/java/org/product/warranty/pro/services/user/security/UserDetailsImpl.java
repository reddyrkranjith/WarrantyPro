package org.product.warranty.pro.services.user.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{
	
	 private static final long serialVersionUID = -6509897037222767090L;
	  
	 private Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	 private String password;
	 private String username;
	 private String email;
	 
	 public UserDetailsImpl(String username , String password, String email, Set<GrantedAuthority> authorities) {
		 this.authorities=authorities;
		 this.username=username;
		 this.password=password;
		 this.email=email;
	}

	@Override
	public Set<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
