package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.service.CommentService;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.service.LikesService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.comment.CommentDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;
		
	@PostMapping("/api/comment")
	public ResponseEntity<?> commentSave(
			@Valid
			@RequestBody CommentDto commentDto,
			BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principalDetails
			){
//			if(bindingResult.hasErrors()) {
//
//				Map<String, String> errorMap = new HashMap<>();
//			
//			for(FieldError error : bindingResult.getFieldErrors()) {
//				errorMap.put(error.getField(), error.getDefaultMessage());
//				
//			}
//			
//			//return "?????????";
//			//????????? ?????????????????????
//			//throw new RuntimeException("??????????????? ?????????");
//			throw new CustomValidationApiException("???????????????  api ", errorMap);
//		}
		// AOP ?????? 	
		
		// json	???????????? ????????? @requestBody
		System.out.println(commentDto);
		Comment comment = commentService.????????????(commentDto.getContent(), commentDto.getImageId(), principalDetails.getUser().getId());
		return new ResponseEntity<>(new CMRespDto<>(1, "??????????????????", comment), HttpStatus.CREATED);
		}
	
	@DeleteMapping("/api/comment/{id}")
	public ResponseEntity<?> commentDelete(
			@PathVariable int id
			){
		commentService.????????????(id);
		return new ResponseEntity<>(new CMRespDto<>(1, "??????????????????", null), HttpStatus.OK);
		}
}
