package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {

	private String name; // 필수 
	private String password; //필수 
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	
	// 필수값이 아닌것들이 데이터 없이 올때, 위험
	public User toEntity() {
		return User.builder()
				.name(name)
				.password(password)
				.website(website)
				.bio(bio)
				.gender(gender)
				.phone(phone)
				.build();
				
	}
}
