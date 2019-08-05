package com.jhallat.codeviewide.classtemplate;

import java.util.List;

import com.jhallat.codeviewide.ui.message.MessageEventBus;
import com.jhallat.codeviewide.ui.project.Project;
import com.jhallat.codeviewide.ui.project.ProjectAction;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class ClassTemplatesAction implements ProjectAction {

	private final Project project;
	
	public ClassTemplatesAction(Project project) {
		this.project = project;
	}
	
	@Override
	public String getDescription() {
		return "Class Templates";
	}

	@Override
	public ContextMenu getContextMenu() {

		ClassTemplateFactory factory = new ClassTemplateFactory();
		factory.loadForProject(project);
		
		ContextMenu contextMenu = new ContextMenu();
		Menu newMenu = new Menu("New");
		List<String> templates = factory.getTemplates();
		for (String template : templates) {
			MenuItem menuItem = new MenuItem(template);
			menuItem.setOnAction(event -> {
				project.displayWorkNode(factory.getWorkNode(template));
			});
			newMenu.getItems().add(menuItem);
		}
		contextMenu.getItems().addAll(newMenu);
		return contextMenu;
	}

}
