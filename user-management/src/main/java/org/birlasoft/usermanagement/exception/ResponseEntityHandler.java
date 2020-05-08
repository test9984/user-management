package org.birlasoft.usermanagement.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseEntityHandler extends ResponseEntityExceptionHandler {
	private final MessageSource messageSource;

	@Autowired
	public ResponseEntityHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/*
	 * @ExceptionHandler(Exception.class) public final ResponseEntity<Object>
	 * handleAllException(Exception ex, WebRequest request) throws Exception {
	 * ThirdeyeExceptionResponce exceptionResponce = new
	 * ThirdeyeExceptionResponce(new Date(), ex.getMessage(),
	 * request.getDescription(false)); return new
	 * ResponseEntity<Object>(exceptionResponce, HttpStatus.INTERNAL_SERVER_ERROR);
	 * }
	 */

	@ExceptionHandler(ThirdeyeException.class)
	public final ResponseEntity<Object> handleThirdeyeException(ThirdeyeException ex, WebRequest request)
			throws Exception {
		ThirdeyeExceptionResponce exceptionResponce = new ThirdeyeExceptionResponce(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponce, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) throws Exception {
		ThirdeyeExceptionResponce exceptionResponce = new ThirdeyeExceptionResponce(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponce, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ObjectAlreadyExistException.class)
	public final ResponseEntity<Object> handleAlreadyFoundException(Exception ex, WebRequest request) throws Exception {
		ThirdeyeExceptionResponce exceptionResponce = new ThirdeyeExceptionResponce(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponce, HttpStatus.ALREADY_REPORTED);
	}

	@Override
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		ThirdeyeExceptionResponce exceptionResponce = new ThirdeyeExceptionResponce(new Date(), errors,
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponce, headers, status);

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ConstraintViolationException.class)
	public Map<String, String> handleConstraintViolation(ConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getConstraintViolations().forEach(cv -> {
			errors.put("message", cv.getMessage());
			errors.put("path", (cv.getPropertyPath()).toString());
		});

		return errors;
	}

}
