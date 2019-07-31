package com.jhallat.codeviewide.ui.bindings;

public class InvalidBindingException extends Exception {
	
	private static final long serialVersionUID = 5860473375284163102L;

	public InvalidBindingException(String message, Exception exception) {
		super(message, exception);
	}

}
