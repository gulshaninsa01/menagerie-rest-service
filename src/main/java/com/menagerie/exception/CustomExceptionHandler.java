package com.menagerie.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.menagerie.constant.Constants;
import com.menagerie.dto.response.BaseResponse;
import com.menagerie.exception.custom.AlreadyExistDataExceptioin;
import com.menagerie.exception.custom.ResourseNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(BaseResponse.builder().status(Constants.FAIL).fieldError(errors).build());
	}

	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<BaseResponse> handleResourceNotFoundException(ResourseNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(BaseResponse.builder().message(e.getMessage()).status(Constants.FAIL).build());
	}

	@ExceptionHandler(AlreadyExistDataExceptioin.class)
	public ResponseEntity<BaseResponse> handleAlreadyExistDataExceptioin(AlreadyExistDataExceptioin e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(BaseResponse.builder().message(e.getMessage()).status(Constants.FAIL).build());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResponse> handleHttpExceptioin(Exception e) {
		log.error("Exception Caught :: {}", e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(BaseResponse.builder().message(e.getMessage()).status(Constants.FAIL).build());
	}
}
