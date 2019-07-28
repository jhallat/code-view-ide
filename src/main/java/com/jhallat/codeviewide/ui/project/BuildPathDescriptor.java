package com.jhallat.codeviewide.ui.project;

import java.util.List;

import com.jhallat.codeviewide.filesystem.Descriptor;

import lombok.Data;

@Data
public class BuildPathDescriptor implements Descriptor {

	public static final String BUILD_PATH_IDENTIFIER = ".buildpath";
	
	private static final long serialVersionUID = -8455612165708052881L;
	private final String context;
	private final List<String> loadedJars; 
	
	@Override
	public String getContext() {
		return context;
	}

	@Override
	public String getIdentifier() {
		return BUILD_PATH_IDENTIFIER;
	}

	
	
}
