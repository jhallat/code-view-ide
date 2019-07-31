package com.jhallat.codeviewide.ui.bindings;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import javafx.scene.control.TextField;

public class BoundTextField extends TextField {

	private BindingModel<?> model;
	private String field;
	private Object modelObject;
	private Method method;
	
	public void bindModel(BindingModel<?> model, String field) throws InvalidBindingException {
		this.model = model;
		this.field = field;
		this.modelObject = model.getValue();
		String fieldName = StringUtils.capitalize(field);
		String setter = String.format("set%s", fieldName);
		String getter = String.format("get%s", fieldName);
		try {
			method = this.modelObject.getClass().getMethod(setter, String.class);
			String currentValue = (String) this.modelObject.getClass().getMethod(getter, null).invoke(this.modelObject, null);
			this.setText(currentValue);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
			String message = String.format("Field %s cannot be bound", field);
			throw new InvalidBindingException(message, exception);
		}
		this.setOnKeyReleased(event -> {
			try {
				this.method.invoke(modelObject, this.getText());
				this.model.fireModified();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
				//TODO find a better way to handle
				exception.printStackTrace();
			} 
		});		
	}
	
	
}
