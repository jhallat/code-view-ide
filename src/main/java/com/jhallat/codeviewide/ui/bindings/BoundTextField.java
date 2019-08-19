package com.jhallat.codeviewide.ui.bindings;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class BoundTextField extends TextField {

	private static final Logger log = LogManager.getLogger(BoundTextField.class);
	
	private BindingModel<?> model;
	private String field;
	
	public void bindModel(BindingModel<?> model, String field)  {
		log.debug(field + " bound");
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
		this.setOnKeyPressed(event -> {
			log.debug("key pressed: "+ event.getCode() + " : " + event.getCharacter());
			if (event.getCode().equals(KeyCode.ENTER)) {
				log.debug("Enter pressed");
				event.consume();
				this.model.fireCommitted();
			}
		});
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
