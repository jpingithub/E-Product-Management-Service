package com.epam.e_commerce.exception;

@SuppressWarnings("serial")
public class ProductNotFoundWithIdException extends RuntimeException {
	public ProductNotFoundWithIdException(String message) {
		super(message);
	}
}
