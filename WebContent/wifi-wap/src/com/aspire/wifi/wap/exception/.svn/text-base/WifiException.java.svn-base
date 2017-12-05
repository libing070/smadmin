package com.aspire.wifi.wap.exception;

public class WifiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2687765184113208532L;
	/**
	 * 错误码
	 */
	private int errorCode;

	public WifiException() {
		super();
	}


	public WifiException(String message,Exception e) {
		super(message,e);
	}
	
	public WifiException(String message) {
		super(message);
	}

	public WifiException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
