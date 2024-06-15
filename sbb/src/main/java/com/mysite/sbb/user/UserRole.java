package com.mysite.sbb.user;

import lombok.Getter;

@Getter
public enum UserRole {
	//사용자 로그인 후 권한 부여
	ADMIN("ROLE_ADMIN"), USER("ROLE_USER");
	
	UserRole(String value){
		this.value = value;
	}
	private String value;
}
