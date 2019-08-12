package com.jhallat.codeviewide.classtemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CodeBlockModel implements CodeElementModel {

	private String content;
	private List<CodeElementModel> elements = new ArrayList<>();
	
	@Override
	public CodeElement getType() {
		return CodeElement.BLOCK;
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
	
	public List<CodeElementModel> getElements() {
		return Collections.unmodifiableList(elements);
	}
	
	public void addElement(CodeElementModel elementModel) {
		this.elements.add(elementModel);
	}

}
