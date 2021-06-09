package com.example.supStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.supStore.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	
	User findByUsername(String username);

	User findByEmail(String email);
}
