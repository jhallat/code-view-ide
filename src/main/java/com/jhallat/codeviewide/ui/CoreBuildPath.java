package com.jhallat.codeviewide.ui;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import com.jhallat.codeviewide.model.ClassModel;

class CoreBuildPath implements BuildPath {

	private static BuildPath singleton = new CoreBuildPath();
	private Set<ClassModel> classModels = new TreeSet<>();
	
	private CoreBuildPath() {}

	public static BuildPath getInstance() {
		return singleton;
	}
		
	protected static CoreBuildPath getCoreInstance() {
		return (CoreBuildPath) singleton;
	}
	
	protected void addClassModel(ClassModel classModel) {
		classModels.add(classModel);
	}

	@Override
	public Set<ClassModel> getClassModels() {
		return Collections.unmodifiableSet(classModels);
	}
}
