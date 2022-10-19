package com.cos.photogramstart.web;

import java.util.UUID;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {

	private final ImageService imageService;  
	
	@GetMapping({"/", "image/story"})  // url 2개 받
	public String story() {
		return "image/story";
		// TODO Auto-generated method stub

	}
	
	@GetMapping("image/popular")
	public String popular() {
		return "image/popular";
		// TODO Auto-generated method stub

	}
	
	@GetMapping("image/upload")
	public String upload() {
		return "image/upload";
		// TODO Auto-generated method stub
		
	}
	
	@PostMapping("/image")
	public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		imageService.사진업로드(imageUploadDto, principalDetails);
		
		// DTO에서 파일은 @notBlank가 되질 않아서 따로 만들어야
		if(imageUploadDto.getFile().isEmpty()) {
			throw new CustomValidationException("이미지가 첨부되지 않았습니다.",	 null);
		}
		return "redirect:/user/"+principalDetails.getUser().getId();
	}
	
	
}
