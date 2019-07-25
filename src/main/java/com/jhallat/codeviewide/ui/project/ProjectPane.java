package com.jhallat.codeviewide.ui.project;

import com.jhallat.codeviewide.ui.BuildPath;
import com.jhallat.codeviewide.ui.WorkNode;
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

public class ProjectPane extends BorderPane implements WorkNodeListener {

	private TabPane tabPane = new TabPane();
	private TreeView<ProjectAction> projectTree = new TreeView<>();
	private final BuildPath buildPath;
	
	
	public ProjectPane(Project project, BuildPath buildPath) {
		super();
		this.buildPath = buildPath;
		project.addWorkNodeListener(this);
		
		SplitPane projectSplitPane = new SplitPane();
		
		Label projectNameLabel = new Label(project.getDirectory().getPath());
		projectNameLabel.getStyleClass().add("project-heading");
		this.setTop(projectNameLabel);
		
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
	
	//private void addWorkNode(WorkNode workNode) {
	//	Tab tab = new Tab("class map");
	//	tab.setContent(workNode.createNode(buildPath));
	//	tabPane.getTabs().add(tab);
	//}

	@Override
	public void workNodeOpened(WorkNode workNode) {
		Tab tab = new Tab(workNode.getDescription());
		tab.setContent(workNode.createNode(buildPath));
		tabPane.getTabs().add(tab);
	}


}
