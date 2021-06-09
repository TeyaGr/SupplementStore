package com.example.supStore.entity.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Role {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
private String roleName;

public Role() {
	super();
}

public Role(Integer roleId, String roleName, Set<UserRole> userRoles) {
	super();
	this.roleId=roleId;
	this.roleName = roleName;
	this.userRoles = userRoles;
}


public Integer getRoleId() {
	return roleId;
}

public void setRoleId(Integer roleId) {
	this.roleId = roleId;
}

public String getRoleName() {
	return roleName;
}

public void setRoleName(String roleName) {
	this.roleName = roleName;
}

public Set<UserRole> getUserRoles() {
	return userRoles;
}

public void setUserRoles(Set<UserRole> userRoles) {
	this.userRoles = userRoles;
}

@OneToMany(mappedBy="role", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
private Set<UserRole> userRoles= new HashSet<>();
}
