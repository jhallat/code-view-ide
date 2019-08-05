package com.jhallat.codeviewide.classtemplate;

import com.jhallat.codeviewide.ui.bindings.BindingModel;
import com.jhallat.codeviewide.ui.bindings.BoundTextField;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ParameterPane extends HBox {

	private ParameterEventHandler handler;
	
	public ParameterPane(int index, ParameterModel parameterModel) {
		super(3);
		BindingModel<ParameterModel> bindingModel = new BindingModel<>(parameterModel);
		BoundTextField typeText = new BoundTextField();
		typeText.setPromptText("Type");
		typeText.getStyleClass().add("text-field-small");
		typeText.bindModel(bindingModel, "type");
		BoundTextField nameText = new BoundTextField();
		nameText.setPromptText("Name");
		nameText.getStyleClass().add("text-field-small");
		nameText.bindModel(bindingModel, "name");
		BoundTextField descriptionText = new BoundTextField();
		descriptionText.setPromptText("Description");
		descriptionText.getStyleClass().add("text-field-large");
		descriptionText.bindModel(bindingModel, "description");
		Button optionsButton = new Button("\u2630");
		this.getChildren().addAll(typeText, nameText, descriptionText, optionsButton);
		bindingModel.setOnModified(event -> {
			if (handler != null) {
				handler.onParameterEvent(new ParameterEvent(index, parameterModel));
			}
		});
	}
	
	public void setOnParameterEvent(ParameterEventHandler handler) {
		this.handler = handler;
	}
	
}
