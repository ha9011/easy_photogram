package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String caption ;// 설명
	
	private String postImageUrl; // 사진을 전송받아서 그 사진을 서버에 특정 폴더에 저장 - db에  그 디렉토리 경로가 저장

	@JoinColumn(name = "userId") //  DB에 user라는 오브젝트 자체가 등록될수없음, 따라서 userId로 FK 명시   
	@ManyToOne   // 이미지 입장에서 many 
	private User user;
	
	// 이미지
	
	// 댓글
	
	
	private LocalDateTime createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
	
}
