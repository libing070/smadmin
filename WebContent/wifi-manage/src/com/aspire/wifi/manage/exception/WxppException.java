package com.aspire.wifi.manage.exception;

public class WxppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2687765184113208532L;
	/**
	 * 错误码
	 */
	private int errorCode;

	public WxppException() {
		super();
	}

	public WxppException(String message) {
		super(message);
	}

	public WxppException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
