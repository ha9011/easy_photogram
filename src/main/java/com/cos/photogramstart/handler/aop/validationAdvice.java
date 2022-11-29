package com.cos.photogramstart.handler.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component // restcintriller, service 도늑 서들이  component를 상속해서 만들어져있음
@Aspect
public class validationAdvice {

	@Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		System.out.println("web api 컨트롤러 ==================");
		// proceedingJoinPoint -> 해당 매소드의 내부에 접근할수있는 매개변수
		// 해당 매소드보다 먼저 실행
		
		
		return proceedingJoinPoint.proceed(); // 그 함수로 다시 돌아가라 ( 그 함수가 실행)
	}

	@Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
	public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

		System.out.println("web 컨트롤러 ==================");

		return proceedingJoinPoint.proceed();
	}
}
