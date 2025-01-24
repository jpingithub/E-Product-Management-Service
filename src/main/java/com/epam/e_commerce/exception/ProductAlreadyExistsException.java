package com.epam.e_commerce.exception;

@SuppressWarnings("serial")
public class ProductAlreadyExistsException extends RuntimeException {

	public ProductAlreadyExistsException(String message) {
		super(message);
	}
}
