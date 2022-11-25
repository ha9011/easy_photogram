package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.comment.CommentRepository;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // IOC, Transaction <-이게 없으면  authcontroller에서 초기화 할때, IOC가 없어서 생성자 초기화를 못
public class CommentService {
	
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	@Transactional
	public Comment 댓글쓰기(String content, int imageId, int userId) {
		// commentRepository.mSave(content, imageId, userId);
		
		// tip
		//  tip처럼 할 경우 return시에 image, user객체는 id값만 있는 빈 객체를 리턴받는다.
		Image image = new Image();
		image.setId(imageId);
		
		// user name 도 필요하기 때문에...
		//User user = new User();
		//user.setId(userId);
		
		User userEntity = userRepository.findById(userId).orElseThrow(()->{
			throw new CustomApiException("유저아이디를 찾을 수 없습니다");
		});
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setUser(userEntity);
		comment.setImage(image);
		
		return  commentRepository.save(comment);
		
	}
	
	@Transactional
	public Comment 댓글삭제() {
		
		return null;
	}
}
