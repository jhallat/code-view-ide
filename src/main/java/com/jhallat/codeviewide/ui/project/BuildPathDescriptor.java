package com.jhallat.codeviewide.ui.project;

import java.util.Collections;
import java.util.List;

import com.jhallat.codeviewide.filesystem.Descriptor;

public class BuildPathDescriptor implements Descriptor {

	public static final String BUILD_PATH_IDENTIFIER = ".buildpath";
	
	private static final long serialVersionUID = -8455612165708052881L;
	private final String context;
	private final List<String> loadedJars; 
	
	public BuildPathDescriptor(String context, List<String> loadedJars) {
		this.context = context;
		this.loadedJars = loadedJars;
	}
	
	@Override
	public String getContext() {
		return context;
	}

	@Override
	public String getIdentifier() {
		return BUILD_PATH_IDENTIFIER;
	}

	public List<String> getLoadedJars() {
		return Collections.unmodifiableList(loadedJars);
	}
	
}
