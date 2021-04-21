package com.timtro247.maven1311.service.impl;

import com.timtro247.maven1311.model.Users;
import com.timtro247.maven1311.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityServiceImpl implements UserDetailsService {
	//the application logger
	private static final Logger LOG = LoggerFactory.getLogger(UserSecurityServiceImpl.class);
	
	private final UserRepository userRepository;

	@Autowired
	public UserSecurityServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByUsernameAndDeletedAtNull(username);
		if(user==null) {
			LOG.warn("Username {} not found",username);
			throw new UsernameNotFoundException("Username "+username+"not found!");
		}
		return user;
	}
}
