package com.jhallat.codeviewide.classtemplate;

import javafx.application.Platform;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CodeContentPane extends VBox implements CodeLineCommittedEventHandler {
	
	private final CodeModel codeModel;
	
	public CodeContentPane(CodeModel codeModel) {
		super(3);
		this.getStyleClass().add("code-form");
		this.codeModel = codeModel;
		displayCode();
	}
	
	
	private void displayCode() {
		if (codeModel.getCodeElements().isEmpty()) {
			CodeLineModel lineModel = new CodeLineModel();
			CodeLinePane linePane = new CodeLinePane(lineModel, 1, 0);
			this.getChildren().add(linePane);
			linePane.setOnCodeLineCommitted(this);
		}
	}


	@Override
	public void onCodeLineEvent(CodeLineEvent codeLineEvent) {
		CodeLineModel lineModel = new CodeLineModel();
		CodeLinePane linePane = new CodeLinePane(lineModel, 1, 0);
		VBox.setVgrow(linePane, Priority.ALWAYS);
		this.getChildren().add(linePane);
		linePane.setOnCodeLineCommitted(this);
		Platform.runLater(() -> {
			linePane.requestFocusOnText();
		}); 
	}
	
}
