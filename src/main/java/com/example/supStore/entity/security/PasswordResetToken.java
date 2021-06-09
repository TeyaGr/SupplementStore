package com.example.supStore.entity.security;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import com.example.supStore.entity.User;

@Entity
@Component
public class PasswordResetToken {

	
	private static final int EXPIRATION =60*24;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String token;
	@OneToOne(targetEntity = User.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(nullable=false,name="user_id")
	
	private User user;
	
	private Date expiryDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpityDate() {
		return expiryDate;
	}

	public void setExpityDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public static int getExpiration() {
		return EXPIRATION;
	}

	public PasswordResetToken(String token, User user) {
		super();
		this.token = token;
		this.user = user;
		this.expiryDate=calculateExpiryDate(EXPIRATION);
	}

	public PasswordResetToken() {
		super();
	}

	private Date calculateExpiryDate(final int expiryTimeInMinutes) {
		final Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE,expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}
	public void updateToken(final String token) {
		this.token=token;
		this.expiryDate=calculateExpiryDate(EXPIRATION);
	}
}


