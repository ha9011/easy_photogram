package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	@JsonIgnoreProperties({"images"})
	@JoinColumn(name = "userId") //  DB에 user라는 오브젝트 자체가 등록될수없음, 따라서 userId로 FK 명시   
	@ManyToOne   // 이미지 입장에서 many, 1사람이 여러사진 올릴수있으니깐 
	private User user;
	
	// 이미지
	@JsonIgnoreProperties({"image"}) // 무한참조 방지
	@OneToMany(mappedBy = "image") // lazy 호출 
	private List<Likes> likes;
	
	// 댓글

	@OrderBy("id DESC")
	@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy = "image") // lazy 호출 
	private List<Comment> comments;
	
	@Transient //DB에 컬럼이 만들어지지 않는다
	private Boolean likeState;
	

	@Transient //DB에 컬럼이 만들어지지 않는다
	private int likeCount;
	
	private LocalDateTime createDate;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	// 오브젝트를 콘솔에 출력할 때 문제가 될 수 있어서 User부분을 출력되지 않게 
//	@Override //User 없
//	public String toString() {
//		return "Image [id=" + id + ", caption=" + caption + ", postImageUrl=" + postImageUrl + ", createDate="
//				+ createDate + "]";
//	}
	
	
	
}
