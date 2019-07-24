package com.jhallat.codeviewide.ui;

import com.jhallat.codeviewide.model.Project;
import com.jhallat.codeviewide.ui.classmap.ClassMapWorkNode;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

public class ProjectPane extends BorderPane {

	private TabPane tabPane = new TabPane();
	private final Project project;
	private final BuildPath buildPath;
	private TreeItem<String> root;
	
	public ProjectPane(Project project, BuildPath buildPath) {
		super();
		this.project = project;
		this.buildPath = buildPath;
		TreeView<String> projectTree = new TreeView<>();
		root = new TreeItem<String>("Project: " + project.getName());
		projectTree.setRoot(root);
		SplitPane projectSplitPane = new SplitPane();
		Label projectNameLabel = new Label(project.getDirectory().getPath());
		projectNameLabel.getStyleClass().add("project-heading");
		this.setTop(projectNameLabel);
		projectSplitPane.getItems().addAll(projectTree, tabPane);
		projectSplitPane.setDividerPositions(0.25);
		this.setCenter(projectSplitPane);
		
		//TOOD This should be handled in a factory
		ContextMenu contextMenu = new ContextMenu();
		MenuItem newMapItem = new MenuItem("New Class Map");
		newMapItem.setOnAction(event -> {
			//TODO Need a class dialog
			WorkNode classMapWorkNode = new ClassMapWorkNode();
			addWorkNode(classMapWorkNode);
		});
		contextMenu.getItems().add(newMapItem);
		projectTree.setContextMenu(contextMenu);
	}
	
	private void addWorkNode(WorkNode workNode) {
		Tab tab = new Tab("class map");
		tab.setContent(workNode.createNode(buildPath));
		tabPane.getTabs().add(tab);
	}


}
