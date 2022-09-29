package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final이 걸린 모든 전역변수에 생성자 걸어
@Controller // IOC, 파일리턴 컨트롤러 <-> restController
public class AuthController {

	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	// 전역변수에 final이 있을 경우, 무조건 초기화해줘야함(or 생성자)
	private final AuthService authService;

// 1. 귀찮은 초기화 방
//	public  AuthController(AuthService authService) {
//		this.authService = authService;
//	}
	
// 2. autowierd 도 가능 하나 문제가 있었던걸로암... 이건 검색해보
	

	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {  // key-value (www-formen....) ,, // 유효성검사 하는 친
		log.info("**************");
		log.info("**************");
		// @valid 해서 유효성검사 오류시, bindingResult에 담김,
		if(bindingResult.hasErrors()) {

			log.info("**************");
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				log.info("__유효성 검사 실패--");
				log.info(error.getDefaultMessage());
			}
		}
		
		
		log.info(signupDto.toString());
		
		// User라는 곳에  signupDto에 담아 전달할 것이다. .
		// 하지만 User에 모든데이터를 격납하지 않을 거고, 회원가입에 필요한 필드값만 넣을 것 이기 때문에
		// @builder 라는 친구 이용!!  
		User user = signupDto.toEntity();
		log.info(user.toString());
		
		log.info("--------------");
		User uesrEntity = authService.signup(user); // 회원가입 
		log.info(uesrEntity.toString());
		
		
		return "auth/signin";
	}
}
