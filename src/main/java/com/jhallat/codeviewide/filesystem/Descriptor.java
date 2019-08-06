package com.jhallat.codeviewide.filesystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Descriptor implements Serializable {
	
	private final String internalId = UUID.randomUUID().toString();
	private final List<DescriptorListener> listeners = new ArrayList<>();
	
	public String getInternalId() {
		return internalId;
	}
	
	public void addListener(DescriptorListener listener) {
		this.listeners.add(listener);
	}
	
	protected void fireModified() {
		for (DescriptorListener listener : listeners) {
			listener.onModified(this);
		}
	}
	
	public abstract String getType();
	public abstract String getContext();
	public abstract String getIdentifier();
}
