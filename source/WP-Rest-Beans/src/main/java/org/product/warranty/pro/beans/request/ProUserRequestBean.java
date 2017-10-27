package org.product.warranty.pro.beans.request;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.product.warranty.pro.beans.custom.validations.ValidPassword;

public class ProUserRequestBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5827781422346836815L;
	
	@NotBlank
	@Size(min = 3, max = 30, message = "Length between 3 to 30")
	private String username;
	
	@NotBlank
	@Pattern(regexp=".+@.+\\.[a-z]+", message = "Please enter valid email.")
	private String email;
	
	@NotBlank(message = "Please enter valid mobile number.")
	private String mobile;
	
	@ValidPassword(message = "Password validation failed.")
	private String password;

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
}
