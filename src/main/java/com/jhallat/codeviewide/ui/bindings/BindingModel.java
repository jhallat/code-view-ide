package com.jhallat.codeviewide.ui.bindings;

public class BindingModel<T> {

	private final T value;
	private ModifiedEvent<T> event;
	
	public BindingModel(T value) {
		this.value = value;
	}
		
	public void setOnModified(ModifiedEvent<T> event) {
		this.event = event;
	}
	
	public T getValue() {
		return this.value;
	}
	
	public void fireModified() {
		this.event.onModified(this.value);
	}
}
