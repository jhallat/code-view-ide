package com.jhallat.codeviewide.ui.bindings;

public class BindingModel<T> {

	private final T value;
	private ModifiedEvent<T> modifiedEvent;
	private CommittedEvent<T> committedEvent;
	
	public BindingModel(T value) {
		this.value = value;
	}
		
	public void setOnModified(ModifiedEvent<T> event) {
		this.modifiedEvent = event;
	}
	
	public void setOnCommitted(CommittedEvent<T> event) {
		this.committedEvent = event;
	}
	
	public T getValue() {
		return this.value;
	}
	
	public void fireModified() {
		if (this.modifiedEvent != null) {
			this.modifiedEvent.onModified(this.value);
		}
	}
	
	public void fireCommitted() {
		if (this.committedEvent != null) {
			this.committedEvent.onCommitted(this.value);
		}
	}
}
