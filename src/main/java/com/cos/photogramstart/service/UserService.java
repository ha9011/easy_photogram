package com.cos.photogramstart.service;

import java.util.function.Supplier;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final SubscribeRepository subscribeRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional(readOnly = true) // select일때는 readonly true로 하면 좋다. 변경감지를 안함 순수 셀렉트로 보기 때문 
	public UserProfileDto 회원프로필(int pageUserId, int principalId) {
		
		UserProfileDto dto = new UserProfileDto();
		
		User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
		});
		
		dto.setUser(userEntity);
		dto.setPageOwnerState(pageUserId==principalId); // true -> 주인 , false -> 주인 아
		dto.setImageCount(userEntity.getImages().size());
		
		int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
		int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);

		dto.setSubscribeCount(subscribeCount);
		dto.setSubscribeState(subscribeState==1);
		
		userEntity.getImages().forEach((image)->{
			image.setLikeCount(image.getLikes().size());
		});
		return dto;
	}
	
	@Transactional
	public User 회원수정(int id, User user) {
		// 1. 영속화
		// 1. 무조건 찾았다 걱정마 get() // 2. 못찾았어 발동시킬께 orElseThrow()
		// User userEntity = userRepository.findById(id).get();
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			
				return new CustomValidationApiException("찾을 수 없는 아이디 입니다.");
			
		});

		// 2. 영속화된 오브젝트 수정 - 더티체킹 (업데이트 완료)

		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);

		userEntity.setName(user.getName());
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());

		return userEntity;
	} // 리턴 하고 마무리 될때, 더티체킹이 일어나서 업데이트가 완료됨.
}
