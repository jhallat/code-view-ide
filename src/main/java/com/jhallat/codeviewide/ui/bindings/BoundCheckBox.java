package com.jhallat.codeviewide.ui.bindings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javafx.scene.control.CheckBox;

public class BoundCheckBox extends CheckBox {

	private static final Logger log = LoggerFactory.getLogger(BoundCheckBox.class);
	
	private BindingModel<?> model;
	private String field;
	
	public void bindModel(BindingModel<?> model, String field) {
		
		MethodInvoker<?> modelInvoker = new MethodInvoker<>(model.getValue());
		this.model = model;
		this.field = field;
		try {
			Boolean currentValue = modelInvoker.get(field);
			this.setSelected(currentValue);
		} catch (MethodInvocationException exception) {
			this.setSelected(false);
			log.error("Unabled to bind field " + field, exception );
		}
		this.setOnAction(event -> {
			try {
				modelInvoker.set(this.field, this.isSelected());
				this.model.fireModified();
			} catch (MethodInvocationException exception) {
				log.error("Unabled to bind field " + field, exception );
			} 
		});
	}
	
}
