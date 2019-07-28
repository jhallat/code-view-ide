package com.jhallat.codeviewide.ui.project;

import java.io.File;

import com.jhallat.codeviewide.ui.CodeViewProperties;
import com.jhallat.codeviewide.ui.WorkNode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PropertiesWorkNode implements WorkNode {

	private final Project project;
	private final CodeViewProperties properties;
	
	PropertiesWorkNode(CodeViewProperties properties, Project project) {
		this.project = project;
		this.properties = properties;
	}

	//TODO Remove buildPath from parameters
	@Override
	public Node createNode() {

		ProjectDescriptor descriptor = project.getDescriptor();
		
		BorderPane propertyPane = new BorderPane();
		propertyPane.getStyleClass().add("code-pane");

		Label nameLabel = new Label("Project Name");
		Label folderLabel = new Label("Folder");
		TextField nameText = new TextField();
		nameText.setPrefWidth(300);
		nameText.setEditable(false);
		nameText.setText(descriptor.getName());
		TextField folderText = new TextField();
		folderText.setPrefWidth(300);
		folderText.setEditable(false);
		folderText.setText(descriptor.getDirectory());
		
		VBox contentPane = new VBox(6);
		
		GridPane filePropertyPane = new GridPane();
		filePropertyPane.getStyleClass().add("dialog-grid");
		filePropertyPane.add(nameLabel, 0, 0);
		filePropertyPane.add(nameText, 1, 0);
		filePropertyPane.add(folderLabel, 0, 1);
		filePropertyPane.add(folderText, 1, 1);
		
		BorderPane buildPathPane = new BorderPane();
		ListView<String> jarListView = new ListView<>();
		refreshJarList(jarListView);
		buildPathPane.setCenter(jarListView);
		Label jarLabel = new Label("Loaded Jars");
		buildPathPane.setTop(jarLabel);
		VBox buttonBar = new VBox();
		buttonBar.getStyleClass().add("button-bar");
		Button addJarButton = new Button("Add Jar");
		addJarButton.getStyleClass().add("dialog-button");
		addJarButton.setOnAction(event -> {
			importJarFile(project.getStage());
			refreshJarList(jarListView);
		});
		buttonBar.getChildren().add(addJarButton);
		buildPathPane.setRight(buttonBar);
		
		contentPane.getChildren().addAll(filePropertyPane, buildPathPane);
		propertyPane.setCenter(contentPane);
		return propertyPane;
	}

	@Override
	public String getDescription() {
		ProjectDescriptor descriptor = this.project.getDescriptor();
		return "Properties: " + descriptor.getName();
	}


	private void refreshJarList(ListView<String> jarListView) {
		ObservableList<String> jarList = FXCollections.observableList(project.getBuildPath().getJars());
		jarListView.getItems().clear();
		jarListView.getItems().addAll(jarList);
	}
	
	private void importJarFile(Stage stage) {
		String lastDirectoryName = properties.get("last.import.jar.directory");
		File lastDirectory = new File(lastDirectoryName);
		FileChooser fileChooser = new FileChooser();
		if (lastDirectory.exists() && lastDirectory.isDirectory()) {
			fileChooser.setInitialDirectory(lastDirectory);
		}
		fileChooser.getExtensionFilters().addAll(
			new FileChooser.ExtensionFilter(".jar Files", "*.jar")	
		);
		File importFile = fileChooser.showOpenDialog(stage);
		project.getBuildPath().addJar(importFile);
        properties.set("last.import.jar.directory", importFile.getParent());
	} 
	
}
