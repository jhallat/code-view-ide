package com.jhallat.codeviewide.classtemplate;

import com.jhallat.codeviewide.ui.bindings.BindingModel;
import com.jhallat.codeviewide.ui.bindings.BoundTextField;

import javafx.scene.layout.VBox;

public class CodeContentPane extends VBox {
	
	private final CodeModel codeModel;
	
	public CodeContentPane(CodeModel codeModel) {
		super(3);
		this.getStyleClass().add("code-form");
		this.codeModel = codeModel;
		displayCode();
	}
	
	
	private void displayCode() {
		if (codeModel.getCodeElements().isEmpty()) {
			BoundTextField lineText = new BoundTextField();
			this.getChildren().add(lineText);
			CodeLineModel lineModel = new CodeLineModel();
			BindingModel<CodeLineModel> bindingLineModel = new BindingModel<>(lineModel);
			lineText.bindModel(bindingLineModel, "content");
			this.codeModel.addCodeElement(lineModel);
		}
	}
	
}
