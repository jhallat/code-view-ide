package com.jhallat.codeviewide.classtemplate;

public interface CodeElementModel {

	public enum CodeElement {
		LINE, BLOCK
	}
	
	CodeElement getType();
}
