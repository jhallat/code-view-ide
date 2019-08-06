package com.jhallat.codeviewide.classtemplate;

import com.jhallat.codeviewide.filesystem.Descriptor;
import com.jhallat.codeviewide.filesystem.DescriptorListener;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class CodePreviewPane extends BorderPane implements DescriptorListener {

	private final TextArea codePreviewText = new TextArea();
	
	public CodePreviewPane(ClassDescriptor classDescriptor) {
		super();
		this.setCenter(codePreviewText);
		codePreviewText.setFont(Font.font("Courier New"));
		codePreviewText.setEditable(false);
		classDescriptor.addListener(this);
	}

	@Override
	public void onModified(Descriptor descriptor) {

		ClassDescriptor code = (ClassDescriptor) descriptor;
		StringBuilder codeBuilder = new StringBuilder();
		codeBuilder.append("package ");
		codeBuilder.append(code.getPackageName());
		codeBuilder.append(";\n\n");
		codeBuilder.append("public class ");
		codeBuilder.append(code.getClassName());
		codeBuilder.append(" {\n");
		codeBuilder.append("}");
		codePreviewText.setText(codeBuilder.toString());
		
	}
	
}
