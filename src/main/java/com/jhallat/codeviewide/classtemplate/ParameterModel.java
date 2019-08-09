package com.jhallat.codeviewide.classtemplate;

import org.apache.commons.lang3.StringUtils;


public class ParameterModel {

	private String type;
	private String name;
	private String description;
	private final MethodModel parent;
	
	public ParameterModel(MethodModel parent) {
		this.parent = parent;
	}
	
	public boolean isEmpty() {
		return StringUtils.isEmpty(type) &&
				StringUtils.isEmpty(name) &&
				StringUtils.isEmpty(description);
	}

	public String getType() {
		if (type == null) {
			return "";
		}
		return type;
	}

	public void setType(String type) {
		this.type = type;
		parent.fireModified();
	}

	public String getName() {
		if (name == null) {
			return "";
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
		parent.fireModified();
	}

	public String getDescription() {
		if (description == null) {
			return "";
		}
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		parent.fireModified();
	}

}
