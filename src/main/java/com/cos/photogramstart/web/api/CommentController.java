package com.cos.photogramstart.web.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.service.CommentService;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.service.LikesService;
import com.cos.photogramstart.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;
		
	@PostMapping("/api/comment")
	public ResponseEntity<?> commentSave(
			@AuthenticationPrincipal PrincipalDetails principalDetails,
			@PathVariable int imageId
			){
		commentService.댓글쓰기();
		return null;
		}
	
	@DeleteMapping("/api/comment")
	public ResponseEntity<?> commentDelete(
			@AuthenticationPrincipal PrincipalDetails principalDetails,
			@PathVariable int imageId
			){
		

		commentService.댓글삭제();
		return null;
	}
}
