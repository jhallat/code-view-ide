package com.jhallat.codeviewide.ui.message;

public interface MessageEvent<T extends Message> {

	public MessageType getType();
	public T getMessage();
	
}
