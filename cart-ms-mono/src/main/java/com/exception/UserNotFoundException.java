package com.exception;

public class UserNotFoundException extends RuntimeException {
	
	private Object fieldValue;

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

	public UserNotFoundException(Object fieldValue) {
		super(String.format("Policy not found with ID : %s",fieldValue));
		this.fieldValue = fieldValue;
	}
}
