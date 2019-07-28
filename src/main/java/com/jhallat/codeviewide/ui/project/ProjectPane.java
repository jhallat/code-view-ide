package com.jhallat.codeviewide.ui.project;

import com.jhallat.codeviewide.ui.WorkNode;

import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ProjectPane extends BorderPane implements WorkNodeListener {

	private TabPane tabPane = new TabPane();
	private TreeView<ProjectAction> projectTree = new TreeView<>();
	
	public ProjectPane(Project project) {
		super();
		project.addWorkNodeListener(this);
		
		SplitPane projectSplitPane = new SplitPane();
		
		ProjectDescriptor projectDescriptor = project.getDescriptor();
		Label projectNameLabel = new Label(projectDescriptor.getDirectory());
		projectNameLabel.getStyleClass().add("project-heading-label");
		HBox heading = new HBox();
		heading.getStyleClass().add("project-heading");
		heading.getChildren().add(projectNameLabel);

		this.setTop(heading);
		
		initializeTreeView(project);
		projectSplitPane.getItems().addAll(projectTree, tabPane);
		projectSplitPane.setDividerPositions(0.25);
		this.setCenter(projectSplitPane);
		
		//TOOD This should be handled in a factory
		/* ContextMenu contextMenu = new ContextMenu();
		MenuItem newMapItem = new MenuItem("New Class Map");
		newMapItem.setOnAction(event -> {
			//TODO Need a class dialog
			WorkNode classMapWorkNode = new ClassMapWorkNode();
			addWorkNode(classMapWorkNode);
		}); 
		contextMenu.getItems().add(newMapItem);
		projectTree.setContextMenu(contextMenu); */
	}
	
	private void initializeTreeView(Project project) {
		
		projectTree.setCellFactory(new ProjectActionCellFactory());
		TreeItem<ProjectAction> root = new TreeItem<ProjectAction>(project.getRootProjectAction());
		projectTree.setRoot(root);
		for (ProjectAction projectAction : project.getProjectActions()) {
			TreeItem<ProjectAction> actionItem = new TreeItem<>(projectAction);
			root.getChildren().add(actionItem);
		}
		
	}

	@Override
	public void workNodeOpened(WorkNode workNode) {
		Tab tab = new Tab(workNode.getDescription());
		tab.setContent(workNode.createNode());
		tabPane.getTabs().add(tab);
	}


}
