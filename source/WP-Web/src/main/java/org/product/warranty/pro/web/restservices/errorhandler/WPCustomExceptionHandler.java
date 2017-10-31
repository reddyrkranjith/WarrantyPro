package org.product.warranty.pro.web.restservices.errorhandler;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.spi.UnauthorizedException;
import org.product.warranty.pro.services.handlers.WPServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings("deprecation")
@ControllerAdvice
public class WPCustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ InvalidRequestException.class })
	protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
		
		InvalidRequestException ire = (InvalidRequestException) e;
		List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

		List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			FieldErrorResource fieldErrorResource = new FieldErrorResource();
			fieldErrorResource.setResource(fieldError.getObjectName());
			fieldErrorResource.setField(fieldError.getField());
			fieldErrorResource.setCode(fieldError.getCode());
			fieldErrorResource.setMessage(fieldError.getDefaultMessage());
			fieldErrorResources.add(fieldErrorResource);
		}

		ErrorResource error = new ErrorResource("Error", ire.getMessage());
		error.setFieldErrors(fieldErrorResources);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return handleExceptionInternal(e, error, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({AccessDeniedException.class})
	protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e, WebRequest request) {
		
		ErrorResource error = new ErrorResource("Error", e.getMessage());
		error.setMessage("Authentication required.");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return handleExceptionInternal(e, error, headers, HttpStatus.UNAUTHORIZED, request);
		
	}
	
	@ExceptionHandler({UnauthorizedException.class})
	protected ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException e, WebRequest request) {
		
		ErrorResource error = new ErrorResource("Error", e.getMessage());
		error.setMessage("Authentication required.");
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return handleExceptionInternal(e, error, headers, HttpStatus.UNAUTHORIZED, request);
		
	}
	
	@ExceptionHandler({WPServiceException.class})
	protected ResponseEntity<Object> handleWPServiceException(WPServiceException e, WebRequest request) {
		
		ErrorResource error = new ErrorResource("Error", e.getMessage());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return handleExceptionInternal(e, error, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
