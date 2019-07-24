package com.jhallat.codeviewide.model;

import java.io.File;

import lombok.Data;

@Data
public class Project {

	private boolean isInitialized = false;
	private final String name;
	private final File directory;
	
	public Project() {
		this.isInitialized = false;
		this.name = null;
		this.directory = null;
	}
	
	public Project(String name, File directory) {
		this.isInitialized = true;
		this.name = name;
		this.directory = directory;
	}
	
}
