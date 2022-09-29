package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//JPA - java persistence api(자바로 데이터를 영구적으로 저정할 수 있는 API를 제공)

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 범호 증가 전략이 데이터베이스를 따라간다. 
	private Long id;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String website;
	
	private String bio; // 자기소개 
	
	private String email;
	
	private String phone;
	
	private String gender;
	
	private String profileLmageUrl;

	private LocalDateTime createDate;
	
	@PrePersist // insert 전에 먼저 생성되어 격
	public void createDate() {
		this.createDate = LocalDateTime.now();
		
	}
	
	
}
