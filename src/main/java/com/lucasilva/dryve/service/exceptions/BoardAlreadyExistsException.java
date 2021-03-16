package com.lucasilva.dryve.service.exceptions;

public class BoardAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BoardAlreadyExistsException(String msg) {
		super(msg);
	}
	
	public BoardAlreadyExistsException(String msg, Throwable cause) {
		super(msg, cause);
	}
}