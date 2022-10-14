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
		
	}
}
