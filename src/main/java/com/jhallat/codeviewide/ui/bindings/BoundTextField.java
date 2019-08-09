package com.jhallat.codeviewide.ui.bindings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.scene.control.TextField;

public class BoundTextField extends TextField {

	private static final Logger log = LoggerFactory.getLogger(BoundTextField.class);
	
	private BindingModel<?> model;
	private String field;
	
	public void bindModel(BindingModel<?> model, String field)  {
		MethodInvoker<?> modelInvoker = new MethodInvoker<>(model.getValue());
		this.model = model;
		this.field = field;
		try {
			String currentValue = modelInvoker.get(field);
			this.setText(currentValue);
		} catch (MethodInvocationException exception) {
			this.setText("#invalid");
			log.error("Unabled to bind field " + field, exception );
		}
		this.setOnKeyReleased(event -> {
			try {
				modelInvoker.set(this.field, this.getText());
				this.model.fireModified();
			} catch (MethodInvocationException exception) {
				log.error("Unabled to bind field " + field, exception );
			} 
		});		
	}
	
	
}
