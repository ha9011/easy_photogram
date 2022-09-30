package com.cos.photogramstart.config.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // IOC
//1. security 때문에 UserDetailsServic가 IOC 올라가져있음
//2. 근데 implements를 하고있는 PrincipalDetailsService가 @service로 IOC 하고있기 때문에/
//3. UserDetailsServic가 사라지고 대체해서 IOC에 격납이 
public class PrincipalDetailsService implements UserDetailsService {
	//시큐리티에서 .loginProcessingUrl("/auth/signin") -> POST  요청이 올경우 낚아챔!  
	
	private static final Logger log = LoggerFactory.getLogger(PrincipalDetailsService.class);

	private final UserRepository userRepository;
	
	// 1. 패스워드는 알아서 체킹하니 신경쓸거 없다.
	// 2. 리이 잘되면 자동으로 세션(PrincipalDetails)을 만든다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 비밀번호는 알아서 처리해줌... 신기함...
		
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) {
			return null;
		}else {
			return new PrincipalDetails(userEntity);
		}
		
	}
	

}
