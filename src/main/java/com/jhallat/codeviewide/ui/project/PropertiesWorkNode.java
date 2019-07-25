package com.jhallat.codeviewide.ui.project;

import com.jhallat.codeviewide.ui.BuildPath;
import com.jhallat.codeviewide.ui.WorkNode;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PropertiesWorkNode implements WorkNode {

	private final Project project;
	
	PropertiesWorkNode(Project project) {
		this.project = project;
	}

	//TODO Remove buildPath from parameters
	@Override
	public Node createNode(BuildPath buildPath) {

		BorderPane propertyPane = new BorderPane();
		Label propertyLabel = new Label("Properties");
		propertyPane.setCenter(propertyLabel);
		return propertyPane;
	}

	@Override
	public String getDescription() {
		return "Properties: " + this.project.getName();
	}

}
