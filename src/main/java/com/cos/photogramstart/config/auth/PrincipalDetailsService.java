package com.cos.photogramstart.config.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // IOC
//1. security 때문에 UserDetailsServic가 IOC 올라가져있음
//2. 근데 implements를 하고있는 PrincipalDetailsService가 @service로 IOC 하고있기 때문에/
//3. UserDetailsServic가 사라지고 대체해서 IOC에 격납이 
public class PrincipalDetailsService implements UserDetailsService {
	//시큐리티에서 .loginProcessingUrl("/auth/signin") -> POST  요청이 올경우 낚아챔!  
	
	
	private static final Logger log = LoggerFactory.getLogger(PrincipalDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("--시큐리티 post 실행되??--");
		// TODO Auto-generated method stub
		return null;
	}
	

}
