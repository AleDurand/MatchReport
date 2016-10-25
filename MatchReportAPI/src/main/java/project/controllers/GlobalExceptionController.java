package project.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import project.exceptions.CustomException;
import project.exceptions.EntityNotFoundException;
import project.models.ErrorModel;

@ControllerAdvice
public class GlobalExceptionController {

	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionController.class);

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler({})
	public ResponseEntity<?> handleBadRequests(CustomException e) {
		ErrorModel message = getErrorMessage(e.getCode(), e.getArgs(), null, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException ex) {
		if (ex.getBindingResult().getFieldError() != null) {
			FieldError error = ex.getBindingResult().getFieldError();
			ErrorModel message = getErrorMessage(error.getCode(), error.getArguments(), error.getField(),
					HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		} else {
			ObjectError error = ex.getBindingResult().getGlobalError();
			ErrorModel message = getErrorMessage(error.getCode(), error.getArguments(), null, HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
		Object[] args = new Object[] { ex.getName(), ex.getRequiredType() };
		ErrorModel message = getErrorMessage("exception.type_mismatch", args, null, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ EntityNotFoundException.class })
	public ResponseEntity<?> handleNotFounds(CustomException e) {
		ErrorModel message = getErrorMessage(e.getCode(), e.getArgs(), null, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	public ResponseEntity<?> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		Object[] args = new Object[] { ex.getMethod() };
		ErrorModel message = getErrorMessage("exception.method_not_supported", args, null,
				HttpStatus.METHOD_NOT_ALLOWED);
		return new ResponseEntity<>(message, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	public ResponseEntity<?> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
		Object[] args = new Object[] { ex.getContentType() };
		ErrorModel message = getErrorMessage("exception.media_type_not_supported", args, null,
				HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		return new ResponseEntity<>(message, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler({ Exception.class, RuntimeException.class })
	public ResponseEntity<?> handleUnexpectedExceptions(Exception e) {
		LOGGER.error("EXCEPTION", e);
		ErrorModel error = new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ErrorModel getErrorMessage(String code, Object[] args, String field, HttpStatus httpStatus) {
		try {
			String message = messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
			return new ErrorModel(httpStatus.value(), field, message);
		} catch (NoSuchMessageException ex) {
			return new ErrorModel(httpStatus.value(), code);
		}
	}
}
