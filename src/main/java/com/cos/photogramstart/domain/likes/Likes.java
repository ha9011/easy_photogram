package com.cos.photogramstart.domain.likes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.subscribe.Subscribe;
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
@Table(uniqueConstraints = { 
		@UniqueConstraint(
				name = "likes_uk",
				columnNames = { "imageId", "userId" }
				)

})
public class Likes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 범호 증가 전략이 데이터베이스를 따라간다.
	private int id;
	

	@JoinColumn(name = "imageId") // 
	@ManyToOne  // default eager 전략!!   //oneToMany는 디폴티가  lazy전
	private Image image; // 하나의 이미지는 여러 라이크를 받을 수 있다, 1 : n

	// 오류가 터지고 나서 잡아봅시다. // 
	@JoinColumn(name = "userId") // 
	@ManyToOne
	private User toUser; // 하나의 유저는 여러 개를 좋아요 할 수 있다. 1:n	

	private LocalDateTime createDate;

	@PrePersist // insert 전에 먼저 생성되어 격납 // native 쿼리 쓰면 격납이 안됨
	public void createDate() {
		this.createDate = LocalDateTime.now();

	}
}
