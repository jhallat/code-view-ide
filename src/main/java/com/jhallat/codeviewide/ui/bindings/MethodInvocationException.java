package com.jhallat.codeviewide.ui.bindings;

public class MethodInvocationException extends Exception {
	
	private static final long serialVersionUID = 6124308521624715571L;

	public MethodInvocationException(String message, Exception exception) {
		super(message, exception);
	}

}
