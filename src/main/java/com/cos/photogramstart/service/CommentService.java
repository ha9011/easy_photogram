package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.comment.CommentRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // IOC, Transaction <-이게 없으면  authcontroller에서 초기화 할때, IOC가 없어서 생성자 초기화를 못
public class CommentService {
	
	private final CommentRepository commentRepository;
	
	@Transactional
	public Comment 댓글쓰기() {
		
		return null;
	}
	
	@Transactional
	public Comment 댓글삭제() {
		
		return null;
	}
}
