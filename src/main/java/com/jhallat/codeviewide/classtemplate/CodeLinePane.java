package com.jhallat.codeviewide.classtemplate;

import com.jhallat.codeviewide.ui.bindings.BindingModel;
import com.jhallat.codeviewide.ui.bindings.BoundTextField;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class CodeLinePane extends HBox {

	private CodeLineModifiedEventHandler modifiedHandler;
	private CodeLineCommittedEventHandler committedHandler;
	private BoundTextField lineText = new BoundTextField();
	
	public CodeLinePane(CodeLineModel codeLineModel, int level, int index) {
		super(3);
		BindingModel<CodeLineModel> bindingModel = new BindingModel<>(codeLineModel);
		lineText.bindModel(bindingModel, "content");
		HBox.setHgrow(lineText, Priority.ALWAYS);
		this.getChildren().add(lineText);
		bindingModel.setOnModified(event -> {
			if (modifiedHandler != null) {
				modifiedHandler.onCodeLineEvent(new CodeLineEvent(codeLineModel));
			}
		});
		bindingModel.setOnCommitted(event -> {
			if (committedHandler != null) {
				committedHandler.onCodeLineEvent(new CodeLineEvent(codeLineModel));
			}
		});
	}
	
	
	public void setOnCodeLineModified(CodeLineModifiedEventHandler handler) {
		this.modifiedHandler = handler;
	}
	
	public void setOnCodeLineCommitted(CodeLineCommittedEventHandler handler) {
		this.committedHandler = handler;
	}
	
	public void requestFocusOnText() {
		this.lineText.requestFocus();
	}
}
