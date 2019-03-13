package org.openkoala.gqc.core;

public abstract class GqcException extends RuntimeException {

	private static final long serialVersionUID = 652615119740294129L;

	public GqcException() {
		super();
	}

	public GqcException(String message) {
		super(message);
	}

	public GqcException(Throwable cause) {
		super(cause);
	}

	public GqcException(String message, Throwable cause) {
		super(message, cause);
	}
}
