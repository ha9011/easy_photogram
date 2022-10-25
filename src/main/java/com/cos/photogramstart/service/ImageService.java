package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;
	
	@Value("${file.path}")
	private String uploadFolder; 
	
	
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID(); //네트워크 상에서 고유성이 보장되는 id를 만들기 위한 표준규
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();//실제파일이름  ex) 1.jpg
		System.out.println("이미지 파일이름 : " + imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		// 통신,  I/O 할때 예외가발생할수있기 때문!
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes()); // param 2 -> 실제 이미지 파
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
		Image imageEntity = imageRepository.save(image);
		
		
		System.out.println(imageEntity); // 주석을 풀면에러가 남!! imageEntity.toString 실행하는데 오류가 
		// imageEntity안에서 @Data의 toString이 User <-> Image를 계속 번갈아가며 호출한다. 
		// 이미지안에 유저있고 유저안에 이미지 있으니 스택오버가 무한으로 쌓임
		// 따라서 toString을 오버라이드 해서 커스텀해야한다.
		
	}
}
