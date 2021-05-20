package com.sanger.muni.error;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sanger.muni.error.exceptions.EntityNotFoundException;
import com.sanger.muni.error.exceptions.FindEntityByIdNotFoundException;
import com.sanger.muni.error.exceptions.NewUserWithDifferentPasswordsException;
import com.sanger.muni.error.exceptions.PasswordNotMismatch;
import com.sanger.muni.error.exceptions.SearchEntityNoResultException;
import com.sanger.muni.error.exceptions.UserNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ EntityNotFoundException.class, SearchEntityNoResultException.class, UserNotFoundException.class,
			FindEntityByIdNotFoundException.class })
	public ResponseEntity<ApiError> handleNotFound(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

	@ExceptionHandler({ NewUserWithDifferentPasswordsException.class, PasswordNotMismatch.class })
	public ResponseEntity<ApiError> handleBadRequest(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<ApiError> handleContrainValidationError(ConstraintViolationException ex) {

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Error");

		String error = ex.getCause().getMessage();

		// Case duplicate element
		Pattern pattern = Pattern.compile("^Duplicate\\s\\w+\\s\\'\\w+\\'", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(error);
		boolean matchFound = matcher.find();
		if (matchFound) {
			String messageDulicate = matcher.group(0);
			String messageDulicateTrim = messageDulicate.replace("'", "");
			apiError.setMessage(messageDulicateTrim);
		}

		// Case delete element have relations
		if (error.startsWith("Cannot delete")) {
			apiError.setMessage("can't delete this item, it has related items");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<ApiError> handleDataIntegrityViolation(DataIntegrityViolationException ex) {

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
				ex.getMostSpecificCause().getMessage() + " constrain validation exception");

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Validation error", errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, ex.getMessage());
		return ResponseEntity.status(status).headers(headers).body(apiError);
	}

}
