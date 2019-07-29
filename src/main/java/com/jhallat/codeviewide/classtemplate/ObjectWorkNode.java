package com.jhallat.codeviewide.classtemplate;

import com.jhallat.codeviewide.ui.WorkNode;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ObjectWorkNode implements WorkNode {

	@Override
	public Node createNode() {
		
		BorderPane objectPane = new BorderPane();
		objectPane.getStyleClass().add("code-pane");
		VBox objectContentPane = new VBox(6);
		VBox methodContentPane = new VBox(3);
		
		GridPane classForm = new GridPane();
		
		Label packageLabel = new Label("Package");
		TextField packageText = new TextField();
		packageText.setPrefWidth(300);
		Label classLabel = new Label("Class Name");
		TextField classText = new TextField();
		classText.setPrefWidth(300);

		classForm.getStyleClass().add("dialog-grid");
		classForm.add(packageLabel, 0, 0);
		classForm.add(packageText, 1, 0);
		classForm.add(classLabel, 0, 1);
		classForm.add(classText, 1, 1);
		
		Button addMethodButton = new Button("New Method");
		addMethodButton.getStyleClass().add("dialog-button");
		addMethodButton.setOnAction(event -> {
			Node methodPane = createMethodPane();
			methodContentPane.getChildren().add(methodPane);
		});
		
		objectContentPane.getChildren().addAll(classForm, methodContentPane, addMethodButton);
		
		objectPane.setCenter(objectContentPane);
		
		return objectPane;
	}

	@Override
	public String getDescription() {
		return "Object";
	}
	
	private Node createMethodPane() {
		
		GridPane methodForm = new GridPane();
		methodForm.getStyleClass().add("method-form");
		
		Label methodNameLabel = new Label("Name");
		TextField methodNameText = new TextField();
		Label methodDescriptionLabel = new Label("Description");
		TextArea methodDescriptionText = new TextArea();	
		methodDescriptionText.setPrefRowCount(3);
		Label returnLabel = new Label("Returns");
		TextField returnText = new TextField();
		
		methodForm.add(methodNameLabel, 0, 0);
		methodForm.add(methodNameText, 1, 0);
		methodForm.add(methodDescriptionLabel, 2, 0);
		methodForm.add(methodDescriptionText, 2, 1, 2, 3);
		methodForm.add(returnLabel, 0, 3);
		methodForm.add(returnText, 1, 3);
		
		return methodForm;
	}
}
