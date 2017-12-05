package com.aspire.shakewxpp.wap.exception;

public class SubcriptionException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2142681012300261490L;
	/**
	 * 错误码
	 */
	private int errorCode;

	public SubcriptionException() {
		super();
	}

	public SubcriptionException(String message) {
		super(message);
	}

	public SubcriptionException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
