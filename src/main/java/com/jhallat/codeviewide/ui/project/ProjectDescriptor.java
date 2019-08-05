package com.jhallat.codeviewide.ui.project;

import com.jhallat.codeviewide.filesystem.Descriptor;

public class ProjectDescriptor implements Descriptor {

	private static final long serialVersionUID = 6086660135327322611L;

	public static final String PROJECT_IDENTIFIER = ".project";

	private final String name;
	private final String directory;

	public ProjectDescriptor(String name, String directory) {
		this.name = name;
		this.directory = directory;
	}
	
	public String getContext() {
		return "";
	}
	
	public String getIdentifier() {
		return PROJECT_IDENTIFIER;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDirectory() {
		return this.directory;
	}
}
