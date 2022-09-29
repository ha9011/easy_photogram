package com.cos.photogramstart.web.dto.auth;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

//DTO는 해당 req에 담긴 데이터들을 담기 위함
@Data // getter setter
public class SignupDto {
	private String username;
	private String password;
	private String email;
	private String name;
	
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
