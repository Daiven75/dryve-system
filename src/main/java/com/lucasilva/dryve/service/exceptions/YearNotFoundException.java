package com.lucasilva.dryve.service.exceptions;

public class YearNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public YearNotFoundException(String msg) {
		super(msg);
	}
	
	public YearNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}