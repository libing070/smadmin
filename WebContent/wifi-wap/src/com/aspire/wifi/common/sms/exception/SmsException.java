package com.aspire.wifi.common.sms.exception;

/**
 * 短信客户端异常.
 */
public class SmsException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * 构造方法.
	 */
	public SmsException() {
		super();
	}

	/**
	 * 构造方法.
	 * 
	 * @param message
	 *            异常信息
	 */
	public SmsException(String message) {
		super(message);
	}

	/**
	 * 构造方法.
	 * 
	 * @param message
	 *            异常信息
	 * @param cause
	 *            异常对象
	 */
	public SmsException(String message, Throwable cause) {
		super(message, cause);
	}

}
