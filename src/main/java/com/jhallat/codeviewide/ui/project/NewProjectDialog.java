package com.jhallat.codeviewide.ui.project;

import java.io.File;

import com.jhallat.codeviewide.ui.CodeViewProperties;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class NewProjectDialog extends Dialog<ProjectModel> {

	public static final String LAST_IMPORT_JAR_DIRECTORY_PROPERTY = "last.import.jar.directory";
	
	public NewProjectDialog(Stage owner, CodeViewProperties properties) {
		super();
		this.setTitle("New Project");
		this.getDialogPane().getStylesheets().add("style.css");
		this.getDialogPane().setPrefWidth(500);
		BorderPane dialogPane = new BorderPane();
		
		Label nameLabel = new Label("Project Name");
		Label folderLabel = new Label("Folder");
		TextField nameText = new TextField();
		nameText.setPrefWidth(300);
		TextField folderText = new TextField();
		folderText.setPrefWidth(300);
		Button browseButton = new Button("Browse");
		browseButton.setOnAction(event -> {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			String lastDirectoryName = properties.get(LAST_IMPORT_JAR_DIRECTORY_PROPERTY);
			File lastDirectory = new File(lastDirectoryName);
			if (lastDirectory != null && lastDirectory.exists() && lastDirectory.isDirectory()) {
				directoryChooser.setInitialDirectory(lastDirectory);
			}
			File projectDirectory = directoryChooser.showDialog(owner);
			if (projectDirectory != null && projectDirectory.exists() && projectDirectory.isDirectory()) {
				folderText.setText(projectDirectory.getPath());
			}
		});
		
		GridPane contentPane = new GridPane();
		contentPane.getStyleClass().add("dialog-grid");
		contentPane.add(nameLabel, 0, 0);
		contentPane.add(nameText, 1, 0);
		contentPane.add(folderLabel, 0, 1);
		contentPane.add(folderText, 1, 1);
		contentPane.add(browseButton, 2, 1);
		dialogPane.setCenter(contentPane);
		
		Button addButton = new Button("Create Project");
		addButton.getStyleClass().add("dialog-button");
		addButton.setOnAction(event -> {
			this.setResult(new ProjectModel(nameText.getText(), folderText.getText(), false));
			this.close();
		});
		Button cancelButton = new Button("Cancel");
		cancelButton.getStyleClass().add("dialog-button");
		cancelButton.setOnAction(event -> {
			this.setResult(new ProjectModel("","", true));
			this.close();			
		});
		HBox buttonBar = new HBox(6);
		buttonBar.getStyleClass().add("button-bar");
		HBox.setHgrow(addButton, Priority.ALWAYS);
		HBox.setHgrow(cancelButton, Priority.ALWAYS);
		buttonBar.getChildren().addAll(addButton, cancelButton);
		buttonBar.setAlignment(Pos.CENTER_RIGHT);
		dialogPane.setBottom(buttonBar);
		
		this.getDialogPane().setContent(dialogPane);
	}
	
}
