package com.example.supStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.supStore.entity.security.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByRoleName(String roleName);
}
