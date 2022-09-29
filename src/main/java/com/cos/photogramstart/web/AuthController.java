package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.web.dto.auth.SignupDto;

@Controller // IOC, 파일리턴 컨트롤러 <-> restController
public class AuthController {

	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	@PostMapping("/auth/signup")
	public String signup(SignupDto signupDto) {  // key-value (www-formen....)
		log.info(signupDto.toString());
		log.info("실행됨???->> 가입후 로그인페이지 이동");
		
		return "auth/signin";
	}
}
