package com.jhallat.codeviewide.classtemplate;

import java.util.List;

import lombok.Data;

@Data
public class MethodModel {

	private String description;
	private String name;
	private String returnType;
	private List<String> parameter; 
	
}
