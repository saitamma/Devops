package com.cisco.ca.cstg.pdi.exceptions;

public class LicenseParsingException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7776953212263918214L;

	public LicenseParsingException(Throwable throwable) {
		super(throwable);
	}

	public LicenseParsingException(String message) {
		super(message);
	}

	public LicenseParsingException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
