package com.jhallat.codeviewide.classtemplate;

import com.jhallat.codeviewide.filesystem.Descriptor;

public class ClassDescriptor extends Descriptor {

	private static final long serialVersionUID = 6975370098892187485L;
	private String packageName;
	private String className;
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
		fireModified();
	}
	
	public String getPackageName() {
		if (packageName == null) {
			return "";
		} else {
			return packageName;
		}
	}

	public String getClassName() {
		if (className == null) {
			return "";
		} else {
			return className;
		}
	}
	
	public void setClassName(String className) {
		this.className = className;
		fireModified();
	}
	
	@Override
	public String getContext() {
		// TODO Convert to folder path
		return packageName;
	}

	@Override
	public String getType() {
		return "Class";
	}
	
	@Override
	public String getIdentifier() {
		if (className == null || className.isBlank()) {
			return "class name required";
		} else {
			return className;
		}
	}

}
