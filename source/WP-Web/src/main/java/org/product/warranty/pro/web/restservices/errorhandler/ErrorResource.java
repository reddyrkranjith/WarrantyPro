package org.product.warranty.pro.web.restservices.errorhandler;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResource {
	
	private String status;
	
	private String message;
	
	private List<FieldErrorResource> fieldErrors;

	public ErrorResource() {
	}

	public ErrorResource(String status, String message) {
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

	public List<FieldErrorResource> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorResource> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
}