package com.cos.photogramstart.domain.subscribe;

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

import com.cos.photogramstart.domain.user.User;

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
@Table(uniqueConstraints = { 
		@UniqueConstraint(
				name = "subscribe_uk",
				columnNames = { "fromUserId", "toUserId" }
				)

})
public class Subscribe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 범호 증가 전략이 데이터베이스를 따라간다.
	private int id;

	@JoinColumn(name = "fromUserId") // fromUser_Id 가 디폴
	@ManyToOne
	private User fromUser;

	@JoinColumn(name = "toUserId")
	@ManyToOne
	private User toUser;

	private LocalDateTime createDate;

	@PrePersist // insert 전에 먼저 생성되어 격
	public void createDate() {
		this.createDate = LocalDateTime.now();

	}

}
