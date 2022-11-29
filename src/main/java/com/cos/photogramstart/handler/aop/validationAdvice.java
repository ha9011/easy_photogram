package com.cos.photogramstart.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;

@Component // restcintriller, service 도늑 서들이 component를 상속해서 만들어져있음
@Aspect
public class validationAdvice {

	@Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		System.out.println("web api 컨트롤러 ==================");
		// proceedingJoinPoint -> 해당 매소드의 내부에 접근할수있는 매개변수
		// 해당 매소드보다 먼저 실행

		Object[] args = proceedingJoinPoint.getArgs();
		for (Object arg : args) {
			System.out.println(arg);
			if (arg instanceof BindingResult) {
				System.out.println("유효성검사를 하는 함수입니다.");
				BindingResult bindingResult = (BindingResult) arg;
				if (bindingResult.hasErrors()) {

					Map<String, String> errorMap = new HashMap<>();

					for (FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());

					}

					throw new CustomValidationApiException("유효성검사  api ", errorMap);
				}
			}
		}

		return proceedingJoinPoint.proceed(); // 그 함수로 다시 돌아가라 ( 그 함수가 실행)
	}

	@Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
	public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		System.out.println("web 컨트롤러 ==================");

		Object[] args = proceedingJoinPoint.getArgs();
		for (Object arg : args) {
			System.out.println(arg);
			if (arg instanceof BindingResult) {
				System.out.println("유효성검사를 하는 함수입니다.");
				if (arg instanceof BindingResult) {
					System.out.println("유효성검사를 하는 함수입니다.");
					BindingResult bindingResult = (BindingResult) arg;
					if (bindingResult.hasErrors()) {

						Map<String, String> errorMap = new HashMap<>();

						for (FieldError error : bindingResult.getFieldErrors()) {
							errorMap.put(error.getField(), error.getDefaultMessage());

						}

						throw new CustomValidationException("유효성검사  api ", errorMap);
					}
				}
			}

		}
		return proceedingJoinPoint.proceed();
	}
}
