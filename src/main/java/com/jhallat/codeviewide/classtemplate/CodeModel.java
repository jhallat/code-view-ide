package com.jhallat.codeviewide.classtemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CodeModel {

	private List<CodeElementModel> codeElements = new ArrayList<>();
	
	
	public List<CodeElementModel> getCodeElements() {
		return Collections.unmodifiableList(codeElements);
	}
	
	public void addCodeElement(CodeElementModel codeElement) {
		this.codeElements.add(codeElement);
	}
	
	public void removeCodeElement(CodeElementModel codeElement) {
		this.codeElements.remove(codeElement);
	}

}
