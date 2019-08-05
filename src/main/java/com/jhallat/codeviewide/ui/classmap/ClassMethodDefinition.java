package com.jhallat.codeviewide.ui.classmap;

import java.io.Serializable;

public class ClassMethodDefinition implements Serializable {

	private static final long serialVersionUID = -553582849750358290L;

	private final String description;
	private final String path;
	private final boolean isMethod;
	
	public ClassMethodDefinition(String description, String path, boolean isMethod) {
		super();
		this.description = description;
		this.path = path;
		this.isMethod = isMethod;
	}

	public String getDescription() {
		return description;
	}

	public String getPath() {
		return path;
	}

	public boolean isMethod() {
		return isMethod;
	}
}
