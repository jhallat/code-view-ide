package com.jhallat.codeviewide.ui.bindings;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

public class MethodInvoker<T> {

	private T root;
	
	public MethodInvoker(T root) {
		this.root = root;
	}
	
	public <S> S get(String fieldName) throws MethodInvocationException {
		
		String getMethodName = "get" + StringUtils.capitalize(fieldName);
		try {
			Method getMethod = root.getClass().getMethod(getMethodName, null);
			S value = (S) getMethod.invoke(root, null);
			return value;
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
			throw new MethodInvocationException("Error getting " + fieldName, exception);
		}


	}
	
	public <S> void set(String fieldName, S value) throws MethodInvocationException {
		
		String setMethodName = "set" + StringUtils.capitalize(fieldName);
		try {
			Method setMethod = root.getClass().getMethod(setMethodName, value.getClass());
			setMethod.invoke(root, value);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
			throw new MethodInvocationException("Error setting " + fieldName, exception);
		}
		
		
	}
	
}
