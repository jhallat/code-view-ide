package com.jhallat.codeviewide.classtemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassModel {

	private String packageName;
	private String className;
	private List<MethodModel> methods = new ArrayList<>();
	private List<ClassModelListener> listeners = new ArrayList<>();
	
	public List<MethodModel> getMethods() {
		return Collections.unmodifiableList(methods);
	}
	
	public void addMethod(MethodModel method) {
		methods.add(method);
		fireModified();
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
		fireModified();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
		fireModified();
	}
	
	void fireModified() {
		for (ClassModelListener listener : listeners) {
			listener.onModified(this);
		}
	}
	
	public void addListener(ClassModelListener listener) {
		this.listeners.add(listener);
	}
	
}
