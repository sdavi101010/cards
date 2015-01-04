package com.scottdavidson.cards.util;

public class CardUtilException extends RuntimeException {
	
	private static final long serialVersionUID = 444495505216601768L;

	public CardUtilException() {
		super();
	}
	
	public CardUtilException(String message) {
		super(message);
	}

	public CardUtilException(String message, Throwable cause) {
		super(message, cause);
	}

	
}
