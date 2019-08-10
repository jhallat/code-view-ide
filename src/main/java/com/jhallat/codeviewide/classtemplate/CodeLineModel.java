package com.jhallat.codeviewide.classtemplate;

public class CodeLineModel implements CodeElementModel {

	private String content;
	
	@Override
	public CodeElement getType() {
		return CodeElement.LINE;
	}

	public String getContent() {
		if (content == null) {
			return "";
		} else {
			return "";
		}
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
