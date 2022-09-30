package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// 어노테이션 없어도 자동으로 등록 !!  jparepository를 상속하기 때문
public interface UserRepository extends JpaRepository<User, Integer> {

		//JPA 쿼리 메서드 
	User findByUsername(String username);
	
	
	
}
