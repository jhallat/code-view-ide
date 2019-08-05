package com.jhallat.codeviewide.classtemplate;

public class ParameterEvent {

	private final int index;
	private final ParameterModel parameterModel;

	public ParameterEvent(int index, ParameterModel parameterModel) {
		this.index = index;
		this.parameterModel = parameterModel;
	}

	public int getIndex() {
		return index;
	}

	public ParameterModel getParameterModel() {
		return parameterModel;
	}
	
}
