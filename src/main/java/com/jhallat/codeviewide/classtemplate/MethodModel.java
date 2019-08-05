package com.jhallat.codeviewide.classtemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MethodModel {

	private String description;
	private String name;
	private String returnType;
	private final List<ParameterModel> parameters = new ArrayList<>(); 
	
	
	public List<ParameterModel> getParameters() {
		return Collections.unmodifiableList(parameters);
	}
	
	public void addParameter(ParameterModel  parameterModel) {
		this.parameters.add(parameterModel);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
}
