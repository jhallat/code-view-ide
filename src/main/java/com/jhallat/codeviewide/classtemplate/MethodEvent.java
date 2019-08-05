package com.jhallat.codeviewide.classtemplate;

public class MethodEvent {

	private final int index;
	private final MethodModel methodModel;

	private MethodEvent(int index, MethodModel methodModel) {
		this.index = index;
		this.methodModel = methodModel;
	}

	public int getIndex() {
		return index;
	}

	public MethodModel getMethodModel() {
		return methodModel;
	}
	
}
