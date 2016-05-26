package com.sstacorp.colectivo.security.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sstacorp.colectivo.jpa.repositories.SysUserRepository;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private SysUserRepository sysUserRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		return sysUserRepository.findByUsername(username);
	}

}
