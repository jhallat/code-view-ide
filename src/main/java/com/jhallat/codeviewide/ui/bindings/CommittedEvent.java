package com.jhallat.codeviewide.ui.bindings;

public interface CommittedEvent<T> {

	void onCommitted(T model);
	
}
