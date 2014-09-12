package com.order.dao;

@SuppressWarnings("serial")
public class DAOObjectNotFoundException extends RuntimeException {
	public DAOObjectNotFoundException() {}

	public DAOObjectNotFoundException(String message) {
		super(message);
	}

	public DAOObjectNotFoundException(Throwable cause) {
		super(cause);
	}

	public DAOObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
