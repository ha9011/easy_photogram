package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {

	
	private final UserService userService;
	private final SubscribeService subscribeService;
	
	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(
			@PathVariable int  id, 
			@Valid UserUpdateDto userupdateDto,
			BindingResult bindingResult,  // 반드시 @valid 뒤에 있는 친구 뒤에 있어야
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(bindingResult.hasErrors()) {

			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				
			}
			
			//return "오류남";
			//강제로 에러발생시키기
			//throw new RuntimeException("유효성검사 실패함");
			throw new CustomValidationApiException("유효성검사  api ", errorMap);
		}else {

			
			User userEntity = userService.회원수정(id, userupdateDto.toEntity());
			
			//세션에 새롭게 변경된 유저정보 교체
			principalDetails.setUser(userEntity);
			//return new CMRespDto<>(1,"회원수정완료",userEntity);	
			// userEntity <- 응답 시 모든 getter 함수가 호출되고 JSON으로 파싱하여 응답한다.
			//따라서 User <-> Image 무한반복  
			return new CMRespDto<>(1,"회원수정완료",userEntity);	
			
		}
		
		
	}
	
	@GetMapping("/api/user/{pageUserId}/subscribe")
	public ResponseEntity<?> subscribeList(
			@AuthenticationPrincipal PrincipalDetails principalDetails,
			@PathVariable int pageUserId)
	{
		List<SubscribeDto> subscribeDto = subscribeService.구독리스트(principalDetails.getUser().getId(), pageUserId);
		
		return new ResponseEntity<>(new CMRespDto<>(1, "구독자 정보 리스트 불러오기 성공",	subscribeDto), HttpStatus.OK);
	}
}
