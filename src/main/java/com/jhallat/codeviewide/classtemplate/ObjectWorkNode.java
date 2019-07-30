package com.jhallat.codeviewide.classtemplate;

import com.jhallat.codeviewide.ui.WorkNode;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
		
		HBox buttonBar = new HBox(6); 
		buttonBar.getStyleClass().add("button-bar");
		Button addMethodButton = new Button("New Method");
		addMethodButton.getStyleClass().add("dialog-button");
		addMethodButton.setOnAction(event -> {
			Node methodPane = createMethodPane();
			methodContentPane.getChildren().add(methodPane);
		});
		Button methodFromTemplateButton = new Button("Method From Template");
		methodFromTemplateButton.getStyleClass().add("dialog-button");
		Button previewClassButton = new Button("Preview Class");
		previewClassButton.getStyleClass().add("dialog-button");
		buttonBar.getChildren().addAll(addMethodButton, methodFromTemplateButton, previewClassButton);
		
		objectContentPane.getChildren().addAll(classForm, methodContentPane, buttonBar);
		
		objectPane.setCenter(objectContentPane);
		
		return objectPane;
	}

	@Override
	public String getDescription() {
		return "Object";
	}
	
	private String createMethodPreviewHeading(MethodModel methodModel) {
		
		String returnType = "void";
		if (methodModel.getReturnType() != null) {
			returnType = methodModel.getReturnType();
		}
		
		String heading = String.format("public %s %s()", returnType, methodModel.getName());
		return heading;
	}
	
	private Node createMethodPane() {

		MethodModel model = new MethodModel();
		
		BorderPane methodPane = new BorderPane();
		methodPane.getStyleClass().add("method-form");

		Label methodPreviewLabel = new Label("public void ()");
		HBox methodPreview = new HBox(3);
		methodPreview.getStyleClass().add("method-preview");
		methodPreview.getChildren().add(methodPreviewLabel);
		methodPane.setTop(methodPreview);
		
		GridPane methodForm = new GridPane();
		methodForm.setHgap(6);
		methodForm.setVgap(6);
		
		Label methodNameLabel = new Label("Name");
		TextField methodNameText = new TextField();
		methodNameText.setOnKeyReleased(event -> {
			model.setName(methodNameText.getText());
			methodPreviewLabel.setText(createMethodPreviewHeading(model));
		});
		
		Label methodDescriptionLabel = new Label("Description");
		TextArea methodDescriptionText = new TextArea();	
		methodDescriptionText.setPrefRowCount(3);
		Label returnLabel = new Label("Returns");
		TextField returnText = new TextField();
		returnText.setOnKeyReleased(event -> {
			model.setReturnType(returnText.getText());
			methodPreviewLabel.setText(createMethodPreviewHeading(model));
		});
		TextField returnDescriptionText = new TextField();

		Label parametersLabel = new Label("Parameters");
		Button addParameterButton = new Button("Add Parameter");
		
		methodForm.add(methodDescriptionLabel, 0, 0);
		methodForm.add(methodDescriptionText, 0, 1, 2, 3);
		methodForm.add(methodNameLabel, 0, 4);
		methodForm.add(methodNameText, 1, 4, 2, 1);
		methodForm.add(returnLabel, 0, 5);
		methodForm.add(returnText, 1, 5, 1, 1);
		methodForm.add(returnDescriptionText, 2, 5, 2, 1);
		methodForm.add(parametersLabel, 0, 6);
		methodForm.add(addParameterButton, 0, 7);
		methodPane.setCenter(methodForm);
		
		return methodPane;
	}
}
