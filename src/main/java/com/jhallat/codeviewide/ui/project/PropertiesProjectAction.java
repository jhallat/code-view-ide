package com.jhallat.codeviewide.ui.project;

import com.jhallat.codeviewide.ui.CodeViewProperties;

public class PropertiesProjectAction implements ProjectAction {

	private final Project project;
	private final CodeViewProperties properties;
	
	public PropertiesProjectAction(CodeViewProperties properties, Project project) {
		this.project = project;
		this.properties = properties;
	}
	
	@Override
	public String getDescription() {
		return "Properties";
	}

	@Override
	public void onDoubleClick() {
		
		PropertiesWorkNode propertyWorkNode = new PropertiesWorkNode(properties, project);
		project.displayWorkNode(propertyWorkNode);
		
	}
}
