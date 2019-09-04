package com.jhallat.codeviewide.domaintemplate;

import com.jhallat.codeviewide.ui.project.Project;
import com.jhallat.codeviewide.ui.project.ProjectAction;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class DomainTemplatesAction implements ProjectAction {

	private final Project project;
	
	public DomainTemplatesAction(Project project) {
		this.project = project;
	}
	
	@Override
	public String getDescription() {
		return "Domains";
	}
	
	@Override
	public ContextMenu getContextMenu() {
		
		ContextMenu contextMenu = new ContextMenu();
		MenuItem newMenu = new MenuItem("New");
		newMenu.setOnAction(event -> {
			project.displayWorkNode(new DomainWorkNode(project));
		});
		contextMenu.getItems().addAll(newMenu);
		return contextMenu;
		
	}
	

}
