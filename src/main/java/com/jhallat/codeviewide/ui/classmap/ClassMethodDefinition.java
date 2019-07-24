package com.jhallat.codeviewide.ui.classmap;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClassMethodDefinition implements Serializable {

	private static final long serialVersionUID = -553582849750358290L;
	private final String description;
	private final String path;
	private final boolean isMethod;
	
	
}
