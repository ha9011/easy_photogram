package com.cos.photogramstart.web.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T> {  // 제네릭을 이용해서 다양하게 받을 수 있다. (전역적으로 이용하기 위함)
	private int code; // 1 성공,-1실
	private String message;
	//private Map<String, String> errorMap; // 다양한 타입으로 받을 수 있기 때문에 제네릭을 써야
	private T data;
}
