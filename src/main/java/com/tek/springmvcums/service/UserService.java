package com.tek.springmvcums.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tek.springmvcums.model.User;

public interface UserService {

	User findById(long id);

	User findByName(String name);

	User saveOrUpdateUser(User user);

	void deleteUserById(long id);

	List<User> findAllUsers();

	void deleteAllUsers();

	public boolean isUserExist(User user);

}
