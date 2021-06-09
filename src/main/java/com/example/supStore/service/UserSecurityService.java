package com.example.supStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.supStore.entity.User;
import com.example.supStore.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
User user =userRepo.findByUsername(username);

if(user==null) {
	throw new UsernameNotFoundException("Username not found");
}
return user;


	}

}
