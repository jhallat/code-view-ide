package com.jhallat.codeviewide.ui.project;

public class ProjectModel {

	private final String name;
	private final String directory;
	private final boolean isCancelled;
	
	public ProjectModel(String name, String directory, boolean isCancelled) {
		super();
		this.name = name;
		this.directory = directory;
		this.isCancelled = isCancelled;
	}

	public String getName() {
		return name;
	}

	public String getDirectory() {
		return directory;
	}

	public boolean isCancelled() {
		return isCancelled;
	}
	
}
