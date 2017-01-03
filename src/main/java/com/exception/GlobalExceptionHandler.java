package com.exception;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j;

@Log4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public void handle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		String error = "{ \"error\": \"exception.default\" }";
		addResponse(response, String.valueOf(request.getRequestURL()), error, ex);
	}
	
	@ExceptionHandler(MyException.class)
	public void handleMyUpdateException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		String error = "{ \"error\": \""+ex.getMessage()+"\" }";
		addResponse(response, String.valueOf(request.getRequestURL()), error, ex);
	}
	
	@ExceptionHandler(DuplicateKeyException.class)
	public void handleDuplicateKeyException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		String error = "{ \"error\": \"exception.duplicateKey\" }";
		addResponse(response, String.valueOf(request.getRequestURL()), error, ex);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public void handleAccessDenied(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		String error = "{ \"error\": \"exception.accessDenied\" }";
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		addResponse(response, String.valueOf(request.getRequestURL()), error, ex);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public void handleValid(HttpServletRequest request, HttpServletResponse response,
			MethodArgumentNotValidException ex) {
		String error = "{ \"error\": \"exception.objectNotValid\" }";
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		addResponse(response, String.valueOf(request.getRequestURL()), error, ex);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public void handleValidated(HttpServletRequest request, HttpServletResponse response,
			ConstraintViolationException ex) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		Set<String> errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.toSet());
		StringBuilder sb = new StringBuilder();
		String space = "";
		sb.append("[");
		for (String s : errors) {
			sb.append(space + "\"" + s + "\"");
			space = ",";
		}
		sb.append("]");
		String error = "{ \"error\": " + sb.toString() + "}";
		addResponse(response, String.valueOf(request.getRequestURL()), error, ex);
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public void handleIntegrity(HttpServletRequest request, HttpServletResponse response,
			DataIntegrityViolationException ex) {
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		String error = "{ \"error\": \"exception.dataIntegrityViolation\" }";
		addResponse(response, String.valueOf(request.getRequestURL()), error, ex);
	}
	
	private void addResponse(HttpServletResponse response, String url, String error, Exception ex) {
		log.info("Exception Occured: URL=" + url + ", errors: " + error);
		 ex.printStackTrace();
		response.setContentType("application/json");
		try {
			response.getOutputStream().println(error);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
