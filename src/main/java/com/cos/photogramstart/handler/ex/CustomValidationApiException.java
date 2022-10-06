package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException{

	// 객체를 구분할 때!!
	
	private static final long serialVersionUID = 1L;
	private String message;
	private Map<String, String> errorMap;
	
public CustomValidationApiException(String message) {
		
		super(message);
		this.message = message+" || TEST";
	};
	
	
	public CustomValidationApiException(String message, Map<String, String> errorMap) {
		
		super(message);
		this.message = message+" || TEST";
		this.errorMap = errorMap;
		System.out.println("____CustomValidationApiException----");
	};
	
	public Map<String,String> getErrorMap() {

		System.out.println("____CustomValidationApiException- getErrorMap---");
		return errorMap;
	}
}
