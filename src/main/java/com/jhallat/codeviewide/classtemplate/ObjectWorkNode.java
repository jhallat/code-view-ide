package com.jhallat.codeviewide.classtemplate;


import com.jhallat.codeviewide.ui.WorkNode;
import com.jhallat.codeviewide.ui.message.HotKeyMessage;
import com.jhallat.codeviewide.ui.message.HotKeyMessageEvent;
import com.jhallat.codeviewide.ui.project.Project;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ObjectWorkNode implements WorkNode {

	private final Project project;
	
	public ObjectWorkNode(Project project) {
		this.project = project;
	}
	
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

		Button previewClassButton = new Button("Preview Class");
		previewClassButton.getStyleClass().add("dialog-button");
		Button addFeatureButton = new Button("Add Feature");
		addFeatureButton.getStyleClass().add("dialog-button");
		buttonBar.getChildren().addAll(previewClassButton, addFeatureButton);

		HBox linkBar = new HBox();
		Hyperlink newMethodLink = new Hyperlink("New Method (Ctrl+M)");
		newMethodLink.setOnAction(event -> {
			Node methodPane = new MethodPane(this.project, 0, new MethodModel());
			methodContentPane.getChildren().add(methodPane);
		});
		Hyperlink methodFromTemplateLink = new Hyperlink("Method from Template (Ctrl+T)");
		linkBar.getChildren().addAll(newMethodLink, methodFromTemplateLink);
				
		objectContentPane.getChildren().addAll(linkBar, classForm, methodContentPane, buttonBar);
		
		objectPane.setCenter(objectContentPane);
		
		objectPane.setOnKeyPressed(event -> {
			if (event.isControlDown()) {
				System.out.println(event.getCharacter());
			}
			System.out.println(event.getCharacter());
		});
		
		project.getMessageEventBus().addReceiver(event -> {
			switch (event.getType()) {
			case HOT_KEY:
				HotKeyMessageEvent hotKeyEvent = (HotKeyMessageEvent) event;
				HotKeyMessage message = hotKeyEvent.getMessage();
				if (message.getHotKey() == HotKeyMessage.HotKey.CTRL_M) {
					Node methodPane = new MethodPane(this.project, 0, new MethodModel());
					methodContentPane.getChildren().add(methodPane);
				}
			}
		});
		
		return objectPane;
	}

	@Override
	public String getDescription() {
		return "Object";
	}
	



}
