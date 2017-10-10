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
	 private String firstname;
	 
	 public UserDetailsImpl(String username , String password, String firstname, Set<GrantedAuthority> authorities) {
		 this.authorities=authorities;
		 this.username=username;
		 this.password=password;
		 this.firstname=firstname;
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
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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
