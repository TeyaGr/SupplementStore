//package com.example.supStore.utility;
//
//import java.util.Locale;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.stereotype.Component;
//
//import com.example.supStore.entity.User;
//
//@Component
//public class MailConstructor {
//
//	@Autowired
//	private org.springframework.core.env.Environment env;
//	
//	public SimpleMailMessage constructResetTokenEmail(String contextPath,User
//			user,Locale locale, String token,String password) {
//		String url=contextPath + "/newUser?token="+token;
//		String message ="\nClik on this link to verify your email address\n";
//		SimpleMailMessage email =new SimpleMailMessage();
//		email.setTo(user.getEmail());
//		email.setSubject("Verify mail");
//		email.setText(url+message);
//		email.setFrom(env.getProperty("support.email"));
//		return email;
//	}
//
//
//}
