package com.jhallat.codeviewide.classtemplate;

public class CodeLineEvent {

	private final CodeLineModel codeLineModel;
	
	public CodeLineEvent(CodeLineModel codeLineModel) {
		this.codeLineModel = codeLineModel;
	}
	
	public CodeLineModel getCodeLineModel() {
		return codeLineModel;
	}
	
}
