package com.cos.photogramstart.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.likes.LikesRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LikesService {

		private final LikesRepository likesRepository;
		
		@Transactional(readOnly = true)// 장점: 영속성 컨텍스트 변경 감지를 해서, 더티체킹, flush(반영)을 하는데, 읽기 전용이라 반영하는 걸 안
		public void  좋아요(int principalId, int imageId){
			likesRepository.mLikes(principalId, imageId);
		}
		@Transactional(readOnly = true)// 장점: 영속성 컨텍스트 변경 감지를 해서, 더티체킹, flush(반영)을 하는데, 읽기 전용이라 반영하는 걸 안
		public void  좋아요취소(int principalId, int imageId){
			likesRepository.mUnLikes(principalId, imageId);
		}
}
