package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscribeDto {
	private int id;
	private String userName;
	private String profileLmageUrl;
	private Integer subscribeState;
	private Integer equalUserState;
}
