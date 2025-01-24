package com.epam.e_commerce.exception;

@SuppressWarnings("serial")
public class NoProductWithCategoryAndIdException extends RuntimeException {
	public NoProductWithCategoryAndIdException(String message) {
		super(message);
	}
}
