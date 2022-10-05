package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

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
	
}
