package com.aspire.wifi.manage.exception;

public class ReSubcriptionException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2142681012300261490L;
	/**
	 * 错误码
	 */
	private int errorCode;

	public ReSubcriptionException() {
		super();
	}

	public ReSubcriptionException(String message) {
		super(message);
	}

	public ReSubcriptionException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
