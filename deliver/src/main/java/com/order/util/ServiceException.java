package com.order.util;

@SuppressWarnings("serial")
public class ServiceException extends RuntimeException {
	private String errorCode;
	private String[] errorArgs;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String[] getErrorArgs() {
		return errorArgs;
	}

	public void setErrorArgs(String[] errorArgs) {
		this.errorArgs = errorArgs;
	}

	public ServiceException(String errorCode) {
		this.errorCode = errorCode;
	}

	public ServiceException(String errorCode, String[] errorArgs) {
		this.errorCode = errorCode;
		this.errorArgs = errorArgs;
	}

	public ServiceException(String errorCode, String[] errorArgs, String message) {
		super(message);
		this.errorCode = errorCode;
		this.errorArgs = errorArgs;
	}
	
//	public String getStackTraceMsg(){
//		StackTraceElement[] element = super.getStackTrace();
//		StringBuffer sb = new StringBuffer();
//		for(int i=0; i<element.length; i++){
//			sb.append(element[i].toString() + "<br>");
//		}
//		return sb.toString();
//	}
}