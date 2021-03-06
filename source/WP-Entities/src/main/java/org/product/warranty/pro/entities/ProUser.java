package org.product.warranty.pro.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity(name = "ProUser")
@Table(name = "wp_users")
public class ProUser{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;
	
	@Column(unique = true, name = "user_key", nullable = false, updatable = false, length = 100)
	private String userkey;
	
	@Column(unique = true, name = "user_name", nullable = false, updatable = false, length = 50)
	private String username;
	
	@Column(unique = true, name = "email", nullable = false, updatable = false, length = 100)
	private String email;
	
	@Column(unique = true, name = "mobile_number", nullable = false, updatable = true, length = 50)
	private String mobile;
	
	@Column(name = "password", nullable = false, updatable = true, length = 100)
	private String password;
	
	@Column(name = "auth_token", nullable = true, updatable = true)
	@Type(type = "text")
	private String auth_token;
	
	@Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;
	
	@Column(name = "updated_date", nullable = false, updatable = true)
	private Date updatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserkey() {
		return userkey;
	}

	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAuth_token() {
		return auth_token;
	}

	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
