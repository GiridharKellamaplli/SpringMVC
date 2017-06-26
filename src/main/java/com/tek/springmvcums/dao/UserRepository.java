package com.tek.springmvcums.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tek.springmvcums.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByUsername(String username);
	
	
}
