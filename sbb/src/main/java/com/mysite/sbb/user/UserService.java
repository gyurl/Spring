package com.mysite.sbb.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository ur;
	private final PasswordEncoder pe;
	
	public SiteUser create(String username, String email, String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);;
		user.setEmail(email);
		/* BCryptPasswordEncoder pe = new BCryptPasswordEncoder(); */
		user.setPassword(pe.encode(password));
		ur.save(user);
		return user;
	}
	
	//사용자명으로 객체 조회
	public SiteUser getUser(String username) {
		Optional<SiteUser> siteUser = ur.findByUsername(username);
		
		if(siteUser.isPresent()) {
			return siteUser.get();
		} else {
			throw new DataNotFoundException("siteuser not found"); //사용자며에 해당하는 데이터가 없을 경우 발생
		}
	}
}
