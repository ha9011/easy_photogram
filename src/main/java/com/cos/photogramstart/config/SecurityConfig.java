package com.cos.photogramstart.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // 해당 파일로 시큐리티를 활성
@Configuration // IOC 
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	//WebMvcAutoConfigurationAdapter -> 시큐리티 설정을 위해서 반드시 상속해야함
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http); // 이 친구가 가로채는 역할을함(인증없을 경우, 로그인 페이지로) //super을 지울 경우 -> 비활성
		http.authorizeRequests()
		.antMatchers("/","/user/**","subscribe/**","/comment/**").authenticated()  // 해당 주소는 인증이 필요
		.anyRequest().permitAll()// 나머지는 모두 허용
		.and()
		.formLogin()
		.loginPage("/auth/signin") // and 이후로권한이 없을 경우, 해당 로그인 페이지로 ㅇ
		.defaultSuccessUrl("/");
	}
}
