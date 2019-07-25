package com.jhallat.codeviewide.ui.project;

public class PropertiesProjectAction implements ProjectAction {

	private final Project project;
	
	public PropertiesProjectAction(Project project) {
		this.project = project;
	}
	
	@Override
	public String getDescription() {
		return "Properties";
	}

	@Override
	public void onDoubleClick() {
		
		PropertiesWorkNode propertyWorkNode = new PropertiesWorkNode(project);
		project.displayWorkNode(propertyWorkNode);
		
	}
}
