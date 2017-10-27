package org.product.warranty.pro.services.handlers;

public class WPServiceException extends Exception{
private static final long serialVersionUID = 7655638662506002906L;
	
	private String message;
	private int errorCode;

	/**
	 * 
	 */
	public WPServiceException() {
		super();
	}
	
	/**
	 * @param mesString
	 * @param errorCode
	 */
	public WPServiceException(String mesString, int errorCode) {
		super(mesString + " :  " + errorCode);
		this.errorCode = errorCode;
		this.message = mesString;
	}
	
	/**
	 * @param e
	 */
	public WPServiceException(Exception e) {
		super(e);
		this.message = e.getMessage();
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		return message + " : " +errorCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
