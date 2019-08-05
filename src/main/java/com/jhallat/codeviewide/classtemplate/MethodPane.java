package com.jhallat.codeviewide.classtemplate;



import com.jhallat.codeviewide.ui.bindings.BindingModel;
import com.jhallat.codeviewide.ui.bindings.BoundTextField;
import com.jhallat.codeviewide.ui.project.Project;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MethodPane extends BorderPane implements ParameterEventHandler {
	
	private final MethodModel methodModel;
	private final int index;
	private final VBox parameterForm = new VBox(3);
	private final Project project;
	
	public MethodPane(Project project, int index, MethodModel methodModel) {
		super();
		this.index = index;
		this.methodModel = methodModel;
		this.project = project;
		this.getStyleClass().add("method-form");
		Label methodPreviewLabel = new Label("public void ()");
		MethodModel model = new MethodModel();
		BindingModel<MethodModel> bindingModel = new BindingModel<>(model);
		bindingModel.setOnModified(event -> {
			methodPreviewLabel.setText(createMethodPreviewHeading(model));
		});

		VBox methodContent = new VBox();
		this.getStyleClass().add("method-form");

		HBox methodPreview = new HBox(3);
		methodPreview.getStyleClass().add("method-preview");
		methodPreview.getChildren().add(methodPreviewLabel);
		this.setTop(methodPreview);
		
		GridPane methodForm = new GridPane();
		methodForm.getStyleClass().add("method-content");
		methodForm.setHgap(6);
		methodForm.setVgap(6);
		
		Label methodNameLabel = new Label("Name");
		BoundTextField methodNameText = new BoundTextField();
		methodNameText.bindModel(bindingModel, "name");
		
		TextArea methodDescriptionText = new TextArea();
		methodDescriptionText.setPromptText("Description");
		methodDescriptionText.setPrefRowCount(3);
		Label returnLabel = new Label("Returns");
		BoundTextField returnText = new BoundTextField();
		returnText.bindModel(bindingModel, "returnType");
		returnText.setPromptText("Type");
		TextField returnDescriptionText = new TextField();
		returnDescriptionText.setPromptText("Description");
		returnDescriptionText.getStyleClass().add("text-field-large");

		Label parametersLabel = new Label("Parameters");
		
		methodForm.add(methodDescriptionText, 0, 0, 5, 3);
		methodForm.add(methodNameLabel, 0, 3, 1, 1);
		methodForm.add(methodNameText, 1, 3, 3, 1);
		methodForm.add(returnLabel, 0, 4, 1, 1);
		methodForm.add(returnText, 1, 4, 1, 1);
		methodForm.add(returnDescriptionText, 2, 4, 3, 1);
		methodForm.add(parametersLabel, 0, 5);
	
		ParameterModel parameterModel = new ParameterModel();
		this.methodModel.addParameter(parameterModel);
		ParameterPane parameterPane = new ParameterPane(0, parameterModel);
		parameterPane.setOnParameterEvent(this);
		parameterForm.getChildren().add(parameterPane);
		methodContent.getChildren().addAll(methodForm, parameterForm);
		
		this.setCenter(methodContent);
	}

	
	private String createMethodPreviewHeading(MethodModel methodModel) {
		
		String returnType = "void";
		if (methodModel.getReturnType() != null) {
			returnType = methodModel.getReturnType();
		}
		
		String heading = String.format("public %s %s()", returnType, methodModel.getName());
		return heading;
	}
	
	@Override
	public void onParameterEvent(ParameterEvent parameterEvent) {
		if (parameterEvent.getIndex() == methodModel.getParameters().size() - 1) {
			ParameterModel eventModel = parameterEvent.getParameterModel();
			if (!eventModel.isEmpty()) {
				ParameterModel parameterModel = new ParameterModel();
				methodModel.addParameter(parameterModel);
				ParameterPane parameterPane = new ParameterPane(methodModel.getParameters().size() - 1, parameterModel);
				parameterPane.setOnParameterEvent(this);
				parameterForm.getChildren().addAll(parameterPane);
			}
		}
	}


}
