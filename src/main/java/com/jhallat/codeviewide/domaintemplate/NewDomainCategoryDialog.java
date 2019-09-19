package com.jhallat.codeviewide.domaintemplate;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class NewDomainCategoryDialog extends Dialog<DomainCategoryModel>{
	
	public NewDomainCategoryDialog() {
		super();
		this.setTitle("New Domain Category");
		this.getDialogPane().getStylesheets().add("style.css");
		this.getDialogPane().setPrefWidth(500);
		
		Label descriptionLabel = new Label("Description");
		TextField descriptionText = new TextField();
		Label colorLabel = new Label("Color");
		ColorPicker colorPicker = new ColorPicker();
		
		GridPane contentPane = new GridPane();
		contentPane.getStyleClass().add("dialog-grid");
		contentPane.add(descriptionLabel, 0, 0);
		contentPane.add(descriptionText, 1, 0);
		contentPane.add(colorLabel, 0, 1);
		contentPane.add(colorPicker, 1, 1);
		
		Button addButton = new Button("Add Category");
		addButton.getStyleClass().add("dialog-button");
		addButton.setOnAction(event -> {
			this.setResult(new DomainCategoryModel(descriptionText.getText(), colorPicker.getValue()));
			this.close();
		});
		Button cancelButton = new Button("Cancel");
		cancelButton.getStyleClass().add("dialog-button");
		cancelButton.setOnAction(event -> {
			this.setResult(null);
			this.close();
		});
		HBox buttonBar = new HBox(6);
		buttonBar.getStyleClass().add("button-bar");
		HBox.setHgrow(addButton, Priority.ALWAYS);
		HBox.setHgrow(cancelButton, Priority.ALWAYS);
		buttonBar.getChildren().addAll(addButton, cancelButton);
		buttonBar.setAlignment(Pos.CENTER_RIGHT);
		
		BorderPane dialogPane = new BorderPane();
		dialogPane.setCenter(contentPane);
		dialogPane.setBottom(buttonBar);
		
		this.getDialogPane().setContent(dialogPane);
		
	}

}
