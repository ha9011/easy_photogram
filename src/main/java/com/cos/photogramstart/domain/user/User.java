package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import org.hibernate.validator.constraints.Length;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private int id;
	
	@Column(unique = true, length = 20)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	
	private String website;
	
	private String bio; // 자기소개 

	@Column(nullable = false)
	private String email;
	
	private String phone;
	
	private String gender;
	
	private String role;
		
	private String profileLmageUrl;

	// mappedBy -> 나는 연관관계의 주인이 아니다. 그러므로 테이블에 칼럼을 만들지맛! 라는 표시 
	// User를select 할때 해당 User id로 등록된 image들을 다 가져와
	// fetch = 설정이 FetchType.LAZY (디폴트) 일 경우 -> User를 Select 할 때 해당 User id의 IMAGE는 '가져오지마' 
	// fetch = 설정이 FetchType.EAGER 일 경우 -> User를 Select 할 때 해당 User id 의 IMAGE도 "가져와" 
	@OneToMany(mappedBy =  "user", fetch = FetchType.EAGER) // Image에서 필드명으로 기입
	@JsonIgnoreProperties({"user"}) //  Image 안에 user는 호출하지마, 무시해!! 
	private List<Image> images; // 즉,유저와 이미지 정보 모두 들고오려면 양방향 매핑이 필
	
	private LocalDateTime createDate;
	
	@PrePersist // insert 전에 먼저 생성되어 격
	public void createDate() {
		this.createDate = LocalDateTime.now();
		
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", website="
				+ website + ", bio=" + bio + ", email=" + email + ", phone=" + phone + ", gender=" + gender + ", role="
				+ role + ", profileLmageUrl=" + profileLmageUrl + ", createDate=" + createDate + "]";
	}
	
	
	
}
