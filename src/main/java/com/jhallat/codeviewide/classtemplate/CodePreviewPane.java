package com.jhallat.codeviewide.classtemplate;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class CodePreviewPane extends BorderPane implements ClassModelListener {

	private final TextArea codePreviewText = new TextArea();
	
	/**
	 * 
	 * @param classModel
	 */
	public CodePreviewPane(ClassModel classModel) {
		super();
		this.setCenter(codePreviewText);
		codePreviewText.setFont(Font.font("Courier New"));
		codePreviewText.setEditable(false);
		classModel.addListener(this);
	}

	@Override
	public void onModified(ClassModel classModel) {

		var codeBuilder = new StringBuilder();
		codeBuilder.append("package ");
		codeBuilder.append(classModel.getPackageName());
		codeBuilder.append(";\n\n");
		codeBuilder.append("public class ");
		codeBuilder.append(classModel.getClassName());
		codeBuilder.append(" {\n");
		
		for (MethodModel method : classModel.getMethods()) {
			codeBuilder.append("/**\n");
			String[] lines = method.getDescription().split("\n");
			for (String line : lines) {
				codeBuilder.append(" * ").append(line).append("\n");
			}
			codeBuilder.append(" * \n");
			if (!method.getReturnType().equals("void")) {
				codeBuilder.append(" * @returns ").append(method.getReturnTypeDescription());
			}
			codeBuilder.append(" */\n");
			codeBuilder.append("public ").append(method.getReturnType()).append(" ")
				.append(method.getName()).append("(");
			
			boolean multipleParameters = false;
			for (ParameterModel parameter : method.getParameters()) {
				if (!parameter.getType().isEmpty()) {
					if (multipleParameters) {
						codeBuilder.append(", ");
					}
					codeBuilder.append(parameter.getType())
					           .append(" ")
					           .append(parameter.getName());
					multipleParameters = true;
				}
			}
			
			codeBuilder.append(")\n");
		}

		codeBuilder.append("}");
		codePreviewText.setText(codeBuilder.toString());
		
	}
	
	
}
