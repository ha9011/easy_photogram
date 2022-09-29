package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // IOC, Transaction <-이게 없으면  authcontroller에서 초기화 할때, IOC가 없어서 생성자 초기화를 못
public class AuthService {
	
	private final UserRepository userRepository;
	
	//회원가
	public User signup(User user) {
		// parameter의 user는 우리가 가져온 것 
		User userEntity = userRepository.save(user);
       //userEntity는 db에서 가져온 
		return userEntity;
	}
}
