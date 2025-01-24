package com.epam.e_commerce.exception;

@SuppressWarnings("serial")
public class NoProductsFroundWithCategoryException extends RuntimeException {
	public NoProductsFroundWithCategoryException(String message) {
		super(message);
	}
}
