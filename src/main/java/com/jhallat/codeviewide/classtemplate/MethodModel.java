package com.jhallat.codeviewide.classtemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MethodModel {

	private final ClassModel parent;
	private String description;
	private String name;
	private String returnType;
	private String returnTypeDescription;
	private final List<ParameterModel> parameters = new ArrayList<>(); 
	
	public MethodModel(ClassModel parent) {
		this.parent = parent;
		this.parent.addMethod(this);
	}
	
	public List<ParameterModel> getParameters() {
		return Collections.unmodifiableList(parameters);
	}
	
	public void addParameter(ParameterModel  parameterModel) {
		this.parameters.add(parameterModel);
		fireModified();
	}

	public String getDescription() {
		if (description == null) {
			return "";
		}
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		fireModified();
	}

	public String getName() {
		if (name == null) {
			return "";
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
		fireModified();
	}

	public String getReturnType() {
		if (returnType == null) {
			return "";
		}
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
		fireModified();
	}
	
	public String getReturnTypeDescription() {
		if (returnType == null) {
			return "";
		}
		return returnTypeDescription;
	}

	public void setReturnTypeDescription(String returnTypeDescription) {
		this.returnTypeDescription = returnTypeDescription;
		fireModified();
	}
	
	public void fireModified() {
		parent.fireModified();
	}
}
