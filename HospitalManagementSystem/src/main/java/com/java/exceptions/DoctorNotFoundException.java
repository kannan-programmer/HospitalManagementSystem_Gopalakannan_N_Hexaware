package com.java.exceptions;

public class DoctorNotFoundException extends Exception {
   
	private static final long serialVersionUID = 1L;

	public DoctorNotFoundException(String message) {
        super(message);
    }
}
