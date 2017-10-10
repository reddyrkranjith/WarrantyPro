package org.product.warranty.pro.repository.exception;

public class WPDataAccessException extends Exception {

	private static final long serialVersionUID = 7239561765428828403L;
	
	public static final int DATA_ACCESS_EXCEPTION_CODE = 2001;
	public static final String DATA_ACCESS_EXCEPTION_MESSAGE = "Data Access exception.";
	
	private String message;
	private int errorCode;
	
	/**
	 * 
	 */
	public WPDataAccessException() {
		super();
	}
	
	/**
	 * @param mesString
	 * @param errorCode
	 */
	public WPDataAccessException(String mesString, int errorCode) {
		super(mesString + " :  " + errorCode);
		this.errorCode = errorCode;
		this.message = mesString;
	}
	
	/**
	 * @param e
	 */
	public WPDataAccessException(Exception e) {
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