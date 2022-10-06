package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {
	
	private final SubscribeRepository subscribeRepository;
	
	@Transactional
	public void 구독하기(int fromUserId, int toUserId) {
		// native query 왜냐하면 user객체로 일일이 담기가 쉽지 않음(복잡)	
		 subscribeRepository.mSubscribe(fromUserId, toUserId);
		
	}
	
	@Transactional
	public void 구독취소하기(int fromUserId, int toUserId) {
		 subscribeRepository.mUnSubscribe(fromUserId, toUserId);
		
	}
}
