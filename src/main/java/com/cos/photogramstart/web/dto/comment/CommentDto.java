package com.cos.photogramstart.web.dto.comment;

import org.springframework.web.multipart.MultipartFile;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class CommentDto {
	private String content;
	private int imageId;
}
