package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final SubscribeRepository subscribeRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Value("${file.path}")
	private String uploadFolder; 
	
	@Transactional(readOnly = true) // select일때는 readonly true로 하면 좋다. 변경감지를 안함 순수 셀렉트로 보기 때문 
	public UserProfileDto 회원프로필(int pageUserId, int principalId) {
		
		UserProfileDto dto = new UserProfileDto();
		
		User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
		});
		
		dto.setUser(userEntity);
		dto.setPageOwnerState(pageUserId==principalId); // true -> 주인 , false -> 주인 아
		dto.setImageCount(userEntity.getImages().size());
		
		int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
		int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);

		dto.setSubscribeCount(subscribeCount);
		dto.setSubscribeState(subscribeState==1);
		
		userEntity.getImages().forEach((image)->{
			image.setLikeCount(image.getLikes().size());
		});
		return dto;
	}
	
	@Transactional
	public User 회원수정(int id, User user) {
		// 1. 영속화
		// 1. 무조건 찾았다 걱정마 get() // 2. 못찾았어 발동시킬께 orElseThrow()
		// User userEntity = userRepository.findById(id).get();
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			
				return new CustomValidationApiException("찾을 수 없는 아이디 입니다.");
			
		});

		// 2. 영속화된 오브젝트 수정 - 더티체킹 (업데이트 완료)

		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);

		userEntity.setName(user.getName());
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());

		return userEntity;
	}

	@Transactional
	public User 회원프로필사진변경(int principalId, MultipartFile profileImageFile) {
		
		UUID uuid = UUID.randomUUID(); //네트워크 상에서 고유성이 보장되는 id를 만들기 위한 표준규
		String imageFileName = uuid+"_"+profileImageFile.getOriginalFilename();//실제파일이름  ex) 1.jpg
		System.out.println("이미지 파일이름 : " + imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		// 통신,  I/O 할때 예외가발생할수있기 때문!
		try {
			Files.write(imageFilePath, profileImageFile.getBytes()); // param 2 -> 실제 이미지 파
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		User userEntity = userRepository.findById(principalId).orElseThrow(()->{
			throw new CustomApiException("유저를 찾을 수 없습니다.");
		});
		userEntity.setProfileLmageUrl(imageFileName);
		return userEntity;
	} //더티체킹으로 업데이트 
}
