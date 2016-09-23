package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import project.exceptions.CustomException;
import project.exceptions.EntityNotFoundException;
import project.exceptions.MessageBundle;
import project.models.ErrorModel;

@ControllerAdvice
public class GlobalExceptionController {

	@Autowired
	private MessageBundle messageBundle;

	@ExceptionHandler({})
	public ResponseEntity<?> handleBadRequests(CustomException e) {
		ErrorModel message = getErrorMessage(e.getCode(), e.getArgs(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException ex) {
		FieldError error = ex.getBindingResult().getFieldError();
		ErrorModel message = getErrorMessage(error.getCode(), error.getArguments(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ EntityNotFoundException.class })
	public ResponseEntity<?> handleNotFounds(CustomException e) {
		ErrorModel message = getErrorMessage(e.getCode(), e.getArgs(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	private ErrorModel getErrorMessage(String code, Object[] args, HttpStatus httpStatus) {
		try {
			String message = messageBundle.messageSource().getMessage(code, args, LocaleContextHolder.getLocale());
			return new ErrorModel(httpStatus.value(), message);
		} catch (NoSuchMessageException ex) {
			return new ErrorModel(httpStatus.value(), code);
		}
	}
}
