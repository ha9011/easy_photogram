package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {
	
	private final SubscribeRepository subscribeRepository;
	private final EntityManager em; // 모든 repository는 EM을 구현해서 만들어져 있는 구현
	
	
	
	@Transactional
	public void 구독하기(int fromUserId, int toUserId) {
		// native query 왜냐하면 user객체로 일일이 담기가 쉽지 않음(복잡)	
		try {

			 subscribeRepository.mSubscribe(fromUserId, toUserId);
		} catch (Exception e) {
			throw new CustomApiException("이미 구독을 하였습니다.");
		}
		
	}
	
	@Transactional
	public void 구독취소하기(int fromUserId, int toUserId) {
		 subscribeRepository.mUnSubscribe(fromUserId, toUserId);
		
	}
	
	@Transactional(readOnly = true)
	public List<SubscribeDto> 구독리스트(int principalId, int pageUserId) {

		System.out.println(" 내 아이디 : " + principalId);
		System.out.println(" pageUserId 아이디 : " + pageUserId);
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u.id, u.username, u.profileLmageUrl, ");
		sb.append("IF ((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId = u.id), 1, 0 ) subscribeState, ");
		sb.append("IF ((?=u.id), 1, 0) equalUserState ");
		sb.append("FROM user u INNER JOIN subscribe s ");
		sb.append("ON u.id = s.toUserId ");
		sb.append("WHERE s.fromUserId = ?"); // 세미콜론 ㄴ
		
		Query query = em.createNativeQuery(sb.toString())
				.setParameter(1,  principalId)
				.setParameter(2,  principalId)
				.setParameter(3,  pageUserId);
		
		JpaResultMapper result = new JpaResultMapper();  //QLRM 라이브러리 // 모델이 아닌, 새로운 조합의 DTO일 경우 쓰임
		//  DTO에 매핍하기 위해 쓰
		List<SubscribeDto> subscribeDtos = result.list(query,  SubscribeDto.class);
				
		 
		return subscribeDtos;
		
	}
	
	
}
