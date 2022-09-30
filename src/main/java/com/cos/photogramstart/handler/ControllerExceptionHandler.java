package com.cos.photogramstart.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;

@RestController
@ControllerAdvice // 모든 익셉션들을 낚아챔!!! (1)
public class ControllerExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(RuntimeException.class) //모든 에러중에 RuntimeException을 낚아 챈다.
	public String validationException1(RuntimeException e) {
		log.info("--runtimeexception 1낚아채기--");
		return e.getMessage(); // 또 문제되는게 runtimeException getMessage는 String임.. 우린 map으로 담고싶다 
	}
	
	@ExceptionHandler(CustomValidationException.class) //모든 에러중에 CustomValidationException을 낚아 챈다.
	public Map<String, String> validationException2(CustomValidationException e) {
		log.info("--CustomValidationException 2낚아채기--");
		return e.getErrorMap(); // 또 문제되는게 runtimeException getMessage는 String임.. 우린 map으로 담고싶다 
	}
}
