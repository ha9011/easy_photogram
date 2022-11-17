package com.cos.photogramstart.web.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.service.LikesService;
import com.cos.photogramstart.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ImageApiController {

	private final ImageService imageService;
	private final LikesService likesService;
		
	@GetMapping("/api/image")
	public ResponseEntity<?> imageStory(@AuthenticationPrincipal PrincipalDetails principalDetails,
			@PageableDefault(size=3) Pageable pageable){
		
		Page<Image> images =  imageService.이미지스토리(principalDetails.getUser().getId(), pageable);
		return new ResponseEntity<>(new CMRespDto<>(1, "성공",images), HttpStatus.OK);
	}
	
	@PostMapping("/api/image/{imageId}/likes")
	public ResponseEntity<?> imageLikes(@AuthenticationPrincipal PrincipalDetails principalDetails,
			@PathVariable int imageId){
		
		likesService.좋아요(principalDetails.getUser().getId(), imageId);
		return new ResponseEntity<>(new CMRespDto<>(1, "좋아요 	성공",null), HttpStatus.CREATED);
	}
	
	@PostMapping("/api/image/{imageId}/unlikes")
	public ResponseEntity<?> imageUnLikes(@AuthenticationPrincipal PrincipalDetails principalDetails,
			@PathVariable int imageId){
		
		likesService.좋아요취소(principalDetails.getUser().getId(), imageId);
		return new ResponseEntity<>(new CMRespDto<>(1, "좋아요 취소 성공", null), HttpStatus.OK);
	}
}
