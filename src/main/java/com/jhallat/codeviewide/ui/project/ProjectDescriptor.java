package com.jhallat.codeviewide.ui.project;

import com.jhallat.codeviewide.filesystem.Descriptor;

import lombok.Data;

@Data
public class ProjectDescriptor implements Descriptor {

	private static final long serialVersionUID = 6086660135327322611L;

	public static final String PROJECT_IDENTIFIER = ".project";

	private final String name;
	private final String directory;
	
	public String getContext() {
		return "";
	}
	
	public String getIdentifier() {
		return PROJECT_IDENTIFIER;
	}
}
