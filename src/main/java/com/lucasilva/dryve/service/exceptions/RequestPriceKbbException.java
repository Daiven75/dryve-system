package com.lucasilva.dryve.service.exceptions;

public class RequestPriceKbbException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequestPriceKbbException(String msg) {
		super(msg);
	}
	
	public RequestPriceKbbException(String msg, Throwable cause) {
		super(msg, cause);
	}
}