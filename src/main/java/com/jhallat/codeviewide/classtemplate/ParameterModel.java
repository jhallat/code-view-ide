package com.jhallat.codeviewide.classtemplate;

import org.apache.commons.lang3.StringUtils;


public class ParameterModel {

	private String type;
	private String name;
	private String description;
	
	public boolean isEmpty() {
		return StringUtils.isEmpty(type) &&
				StringUtils.isEmpty(name) &&
				StringUtils.isEmpty(description);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
