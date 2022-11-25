package com.cos.photogramstart.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	//  pk 값을 주지 않기 때문에 문제가 발생
	/*
	 * @Modifying
	 * 
	 * @Query(
	 * value="INSERT INTO comment(content, imageId, userId, createDate) VALUES(:content, :imageId, :userId, now())"
	 * , nativeQuery = true)
	 Comment mSave(String content, int imageId, int userId);
	*/	
}
