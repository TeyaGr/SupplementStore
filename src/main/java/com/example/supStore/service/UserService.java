package com.example.supStore.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.supStore.entity.User;
import com.example.supStore.entity.security.PasswordResetToken;
import com.example.supStore.entity.security.UserRole;
@Service
public interface UserService {

	PasswordResetToken getPasswordResetToken(final String token
			);
	void  createPasswordResetTokenForUser(final User user, final String token);
	User findByUsername(String username);
	
	User findByEmail(String email);
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
}
