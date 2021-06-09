package com.example.supStore.entity.controller;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.supStore.entity.User;
import com.example.supStore.entity.security.PasswordResetToken;
import com.example.supStore.entity.security.Role;
import com.example.supStore.entity.security.UserRole;
import com.example.supStore.repository.UserRepository;
import com.example.supStore.service.UserSecurityService;
import com.example.supStore.service.UserService;
import com.example.supStore.utility.SecurityUtility;

@Controller
public class HomeController {
@Autowired
private UserService userService;
@Autowired
private UserSecurityService userSecurityService;
@Autowired
UserRepository userRepo;
//	@Autowired
//	private JavaMailSender mailSender;
//	@Autowired
//	private MailConstructor mailConstructor;
	@GetMapping("/")
	public String viewHomepage() {
		return"index";
	}
	@GetMapping("/adminPortal")
	public String viewAdminPortal() {
		return"adminPortal";
	}
	@GetMapping("/adminLogin")
	public String viewAdminLogin() {
		return"adminLogin";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("classActiveLogin", true);
		return "myAccount";
	}
	@RequestMapping("/forgotPassword")
	public String forgetPassword( Model model) {
model.addAttribute("classActiveForgotPassword", true);
return "myAccount";
	}

	@PostMapping("/newUser")
	public String newUserPost(
			HttpServletRequest request,
			@ModelAttribute("email") String userEmail,
			@ModelAttribute("username") String username,
			Model model) 
throws Exception{
		model.addAttribute("classActiveNewAccount", true);
		model.addAttribute("email", userEmail);
		model.addAttribute("username", username);
		if(userService.findByUsername(username)!=null) {
			model.addAttribute("usernameExists", true);
			
		}
		if(userService.findByEmail(userEmail)!=null) {
			model.addAttribute("emailExists", true);
			
		}
		User user = new User();
		user.setUsername(username);
		user.setEmail(userEmail);
		
		String password= SecurityUtility.randomPassword();
		String ecryptedPassword= SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(ecryptedPassword);
		Role role = new Role();
		role.setRoleName("ROLE_USER");
		role.setRoleId(1);
		Set<UserRole>userRoles = new HashSet<>();
		userRoles.add(new UserRole( user, role));
		
		userService.createUser(user,userRoles);
		userRepo.save(user);
		String token =UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(user, token);
		
		String appUrl = "hhtp://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		
//		SimpleMailMessage email = mailConstructor.constructResetTokenEmail(appUrl, user,request.getLocale(), token, password);
//			
//				mailSender.send(email);
//			model.addAttribute("emailSent"," true");
		return "/myAccount";
	}
	
	
	@RequestMapping("/newUser")
	public String newUser(
			Locale locale,
			@RequestParam("token") String token,
			Model model) {
		PasswordResetToken passToken=userService.getPasswordResetToken(token);
		
		if(passToken==null) {
			String message ="Invalid token.";
			model.addAttribute("message", message);
			return "redirect:/badRequest";
		}
		User user =passToken.getUser();
		String username=user.getUsername();
		UserDetails userDetails = new UserSecurityService().loadUserByUsername(username);
		UsernamePasswordAuthenticationToken authentication =new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		model.addAttribute("user", user);
		model.addAttribute("classActiveEdit", true);
		return"/myProfile";
	}
}
