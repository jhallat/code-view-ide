package com.jhallat.codeviewide.ui.project;

import com.jhallat.codeviewide.filesystem.Descriptor;
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
	private final Project project;
	
	public ProjectPane(Project project) {
		super();
		this.project = project;
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

	}
	
	private void initializeTreeView(Project project) {
		
		projectTree.setCellFactory(new ProjectActionCellFactory());
		TreeItem<ProjectAction> root = new TreeItem<ProjectAction>(project.getRootProjectAction());
		root.setExpanded(true);
		projectTree.setRoot(root);
		for (ProjectAction projectAction : project.getProjectActions()) {
			TreeItem<ProjectAction> actionItem = new TreeItem<>(projectAction);
			root.getChildren().add(actionItem);
		}
		
	}

	@Override
	public void workNodeOpened(WorkNode workNode) {
		Descriptor descriptor = workNode.getDescriptor();
		DescriptorTab tab = new DescriptorTab(descriptor);
		tab.setContent(workNode.createNode());
		tabPane.getTabs().add(tab);
	}


}
