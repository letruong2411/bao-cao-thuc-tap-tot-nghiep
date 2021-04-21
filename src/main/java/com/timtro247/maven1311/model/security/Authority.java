package com.timtro247.maven1311.model.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority { // keyword
	private static final long serialVersionUID = 495553029452960980L;
	private final String authority;
	public Authority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		return authority;
	}
	
}
