package com.cos.photogramstart.web.dto.user;

import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {

	@NotBlank
	private String name; // 필수 

	@NotBlank
	private String password; //필수 
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	
	// 필수값이 아닌것들이 데이터 없이 올때, 위험
	public User toEntity() {
		return User.builder()
				.name(name) //password도 마찬가
				.password(password)//패스워드를 기재 안했으면, 공백으로 넣어짐.. 문제가 됨 ->validation 체크 필
				.website(website)
				.bio(bio)
				.gender(gender)
				.phone(phone)
				.build();
				
	}
}
