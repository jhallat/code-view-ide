package com.jhallat.codeviewide.classtemplate;


import com.jhallat.codeviewide.filesystem.Descriptor;
import com.jhallat.codeviewide.ui.WorkNode;
import com.jhallat.codeviewide.ui.message.HotKeyMessageEvent;
import com.jhallat.codeviewide.ui.project.Project;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ClassWorkNode implements WorkNode {

	private final Project project;
	private final ClassDescriptor classDescriptor;
	
	public ClassWorkNode(Project project) {
		this.project = project;
		this.classDescriptor = new ClassDescriptor();
	}
	
	@Override
	public Descriptor getDescriptor() {
		return this.classDescriptor;
	}
	
	@Override
	public Node createNode() {

		SplitPane splitPane = new SplitPane();
		BorderPane objectPane = new BorderPane();

		objectPane.getStyleClass().add("code-pane");
		VBox objectContentPane = new VBox(6);
		VBox methodContentPane = new VBox(3);
		ScrollPane methodScrollPane = new ScrollPane();
		methodScrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		methodScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		methodScrollPane.setContent(methodContentPane);
		
		GridPane classForm = new GridPane();
		
		Label packageLabel = new Label("Package");
		TextField packageText = new TextField();
		packageText.setPrefWidth(300);
		packageText.setOnKeyReleased(event -> {
			this.classDescriptor.setPackageName(packageText.getText());
		});
		Label classLabel = new Label("Class Name");
		TextField classText = new TextField();
		classText.setOnKeyReleased(event -> {
			this.classDescriptor.setClassName(classText.getText());
		});
		classText.setPrefWidth(300);

		classForm.getStyleClass().add("dialog-grid");
		classForm.add(packageLabel, 0, 0);
		classForm.add(packageText, 1, 0);
		classForm.add(classLabel, 0, 1);
		classForm.add(classText, 1, 1);

		HBox linkBar = new HBox();
		Hyperlink newMethodLink = new Hyperlink("New Method (Ctrl+M)");
		newMethodLink.setOnAction(event -> {
			Node methodPane = new MethodPane(this.project, 0, new MethodModel());
			methodContentPane.getChildren().add(methodPane);
		});
		Hyperlink methodFromTemplateLink = new Hyperlink("Method from Template (Ctrl+T)");
		Hyperlink addFeatureLink = new Hyperlink("Add Feature (Ctrl+F)");
		linkBar.getChildren().addAll(newMethodLink, methodFromTemplateLink);
				
		objectContentPane.getChildren().addAll(linkBar, classForm, methodScrollPane);
		
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
				if (hotKeyEvent.getHotKey() == HotKeyMessageEvent.HotKey.CTRL_M) {
					Node methodPane = new MethodPane(this.project, 0, new MethodModel());
					methodContentPane.getChildren().add(methodPane);
				}
			}
		});
		
		CodePreviewPane codePreview = new CodePreviewPane(this.classDescriptor);
		
		splitPane.getItems().addAll(objectPane, codePreview);
		splitPane.setOrientation(Orientation.VERTICAL);
		splitPane.setDividerPositions(0.75);
		return splitPane;
	}


}
