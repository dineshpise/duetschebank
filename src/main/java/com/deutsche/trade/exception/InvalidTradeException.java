package com.deutsche.trade.exception;

public class InvalidTradeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String id;

	public InvalidTradeException(final String id) {
		super("Invalid Trade Exception: " + id);
		this.id = id;
	}

	public String getId() {
		return id;
	}
}