package com.jhallat.codeviewide.model;

import lombok.Data;

@Data
public class ClassModel implements Comparable<ClassModel> {

	private final String className;
	private final Class classDefinition;

	public int compareTo(ClassModel classModel) {
		return this.getClassName().compareTo(classModel.getClassName());
	}
}
