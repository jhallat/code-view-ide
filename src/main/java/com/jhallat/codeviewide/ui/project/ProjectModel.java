package com.jhallat.codeviewide.ui.project;

import lombok.Data;

@Data
public class ProjectModel {

	private final String name;
	private final String directory;
	private final boolean isCancelled;
	
}
