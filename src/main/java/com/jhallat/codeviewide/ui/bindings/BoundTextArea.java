package com.jhallat.codeviewide.ui.bindings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jhallat.deepclasscopy.InvalidPathException;
import com.jhallat.deepclasscopy.MethodPathInvoker;

import javafx.scene.control.TextArea;

public class BoundTextArea extends TextArea {

	private static final Logger log = LoggerFactory.getLogger(BoundTextArea.class);
	
	private BindingModel<?> model;
	private String field;
	
	public void bindModel(BindingModel<?> model, String field)  {
		MethodPathInvoker<?> modelInvoker = new MethodPathInvoker<>(model.getValue());
		this.model = model;
		this.field = field;
		try {
			String currentValue = modelInvoker.get(field);
			this.setText(currentValue);
		} catch (SecurityException | IllegalArgumentException | InvalidPathException exception) {
			this.setText("#invalid");
			log.error("Unabled to bind field " + field, exception );
		}
		this.setOnKeyReleased(event -> {
			try {
				modelInvoker.set(this.field, this.getText());
				this.model.fireModified();
			} catch (IllegalArgumentException | InvalidPathException exception) {
				log.error("Unabled to bind field " + field, exception );
			} 
		});		
	}
	
}
