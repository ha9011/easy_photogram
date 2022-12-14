package com.cos.photogramstart.web;

import org.eclipse.jdt.internal.compiler.batch.Main.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails)  {
		UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
		
		model.addAttribute("dto", dto);
		return "user/profile";
	}	
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable String id,
			@AuthenticationPrincipal PrincipalDetails principalDetails, Model model)  {
		//@AuthenticationPrincipal 을 통해서 시큐리티에 저장된 세션 쉽게 접근!!
		//System.out.println("세션정보 : " + principalDetails.getUser());
		
		// 어려운 방법  , 직접찾음 세션 정
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
		//System.out.println("직접 세션정보 : " + mPrincipalDetails.getUser());

		// jsp에 값을 넘길때 modelAndView 이
		model.addAttribute("principal", principalDetails.getUser());
		return "user/update";
	}	 
}
