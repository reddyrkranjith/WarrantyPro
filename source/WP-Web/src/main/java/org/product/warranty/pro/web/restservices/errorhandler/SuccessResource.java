package org.product.warranty.pro.web.restservices.errorhandler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessResource {
	
private String status;
	
	private String message;
	
	public SuccessResource() {
	}

	public SuccessResource(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
