package com.cos.photogramstart.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends  JpaRepository<Likes, Integer>{

	@Modifying
	@Query(value="INSERT INTO likes(userId, imageId, createDate) VALUES(:principalId, :imageId, now());", nativeQuery=true)
	int mLikes(int principalId, int imageId);
	
	@Modifying
	@Query(value="DELETE FROM likes WHERE userId= :principalId  AND imageId=:imageId ;", nativeQuery=true)
	int mUnLikes(int principalId, int imageId);
	
}
