package com.aspire.shakewxpp.wap.exception;

public class UpdateCoinException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3815748885506933345L;
	/**
	 * 错误码
	 */
	private int errorCode;

	public UpdateCoinException() {
		super();
	}

	public UpdateCoinException(String message) {
		super(message);
	}

	public UpdateCoinException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
