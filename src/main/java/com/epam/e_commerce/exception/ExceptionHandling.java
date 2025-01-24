package com.epam.e_commerce.exception;

import java.time.Instant;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.epam.e_commerce.dto.ExceptionResponse;

@RestControllerAdvice
public class ExceptionHandling {

	@ExceptionHandler(NoSuchElementException.class)
	ResponseEntity<ExceptionResponse> handleNosuchelementFoundException(NoSuchElementException ex,WebRequest web) {
		Instant time = Instant.now();
		String message = ex.getMessage();
		String path = web.getDescription(false);
		Integer status = HttpStatus.NO_CONTENT.value();
		return ResponseEntity.ok().body(new ExceptionResponse(time, message, path, status));
	}
	@ExceptionHandler(ProductNotFoundWithIdException.class)
	ResponseEntity<ExceptionResponse> handleProductNotFoundWithId(ProductNotFoundWithIdException ex,WebRequest web) {
		Instant time = Instant.now();
		String message = ex.getMessage();
		String path = web.getDescription(false);
		Integer status = HttpStatus.NO_CONTENT.value();
		return ResponseEntity.ok().body(new ExceptionResponse(time, message, path, status));
	}
}
