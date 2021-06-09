package com.example.supStore.service;

import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supStore.entity.User;
import com.example.supStore.entity.security.PasswordResetToken;
import com.example.supStore.entity.security.UserRole;
import com.example.supStore.repository.PasswordResetTokenRepository;
import com.example.supStore.repository.RoleRepository;
import com.example.supStore.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(UserService.class);
@Autowired
RoleRepository roleRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	private PasswordResetTokenRepository passResetRepo;
	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		// TODO Auto-generated method stub
		return passResetRepo.findByToken(token);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passResetRepo.save(myToken);
		
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email);
	}

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception  {
		User localUser = userRepo.findByUsername(user.getUsername());
		if(localUser!=null) {
		LOG.info("That username already exists",user.getUsername());
			
		}else {
			for(UserRole ur:userRoles) {
				roleRepo.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			localUser=userRepo.save(user);
		}
		return localUser;
	}

	
}
