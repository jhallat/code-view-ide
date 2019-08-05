package com.jhallat.codeviewide.model;

public class ClassModel implements Comparable<ClassModel> {

	private final String className;
	private final Class classDefinition;

	public ClassModel(String className, Class classDefinition) {
		this.className = className;
		this.classDefinition = classDefinition;
	}
	
	public int compareTo(ClassModel classModel) {
		return this.getClassName().compareTo(classModel.getClassName());
	}
	
	public String getClassName() {
		return this.className;
	}
	
	public Class getClassDefinition() {
		return this.classDefinition;
	}
}
