package com.example.supStore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.supStore.entity.User;
import com.example.supStore.entity.security.Role;
import com.example.supStore.entity.security.UserRole;
import com.example.supStore.service.UserService;
import com.example.supStore.utility.SecurityUtility;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaRepositories



public class SupStoreApplication {
@Autowired
private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(SupStoreApplication.class, args);
	


		
	}
}
