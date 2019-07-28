package com.jhallat.codeviewide.ui.classmap;

import com.jhallat.codeviewide.ui.WorkNode;
import com.jhallat.codeviewide.ui.project.Project;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ClassMapWorkNode implements WorkNode {

	private VBox mapContents = new VBox();
	private Project project;
	
	public ClassMapWorkNode(Project project) {
		this.project = project;
	}
	
	@Override
	public String getDescription() {
		return "not implemented";
	}
	
	@Override
	public Node createNode() {

		SourcePane sourceLayout = new SourcePane(project.getBuildPath());
		
		BorderPane mapLayout = new BorderPane();
		Label mapLabel = new Label("Map");
		mapLayout.setTop(mapLabel);
		mapLayout.setCenter(mapContents);
		
		BorderPane codeLayout = new BorderPane();
		TextArea codeText = new TextArea();
		codeLayout.setCenter(codeText);
		
		TargetPane targetLayout = new TargetPane(project.getBuildPath());
		targetLayout.addMapListener(new MapListener<ClassMethodDefinition>() {
			@Override
			public void itemMapped(ClassMethodDefinition source, ClassMethodDefinition target) {
				Label mapLabel = new Label(source.getPath() + " -> " + target.getPath());
				mapContents.getChildren().add(mapLabel);
				System.out.println();
			}
		});
		
		SplitPane copyLayout = new SplitPane();
		copyLayout.getItems().addAll(sourceLayout, targetLayout, mapLayout);
		copyLayout.setDividerPositions(0.33f, 0.67f);
		
		SplitPane nodeLayout = new SplitPane();
		nodeLayout.getItems().addAll(copyLayout, codeLayout);
		nodeLayout.setOrientation(Orientation.VERTICAL);
		nodeLayout.setDividerPositions(0.75F);
		
		BorderPane codePane = new BorderPane();
		codePane.getStyleClass().add("code-pane");
		codePane.setCenter(nodeLayout);
		
		return codePane;
	}

	
	
}
