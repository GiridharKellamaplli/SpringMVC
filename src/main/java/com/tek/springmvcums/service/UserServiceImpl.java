package com.tek.springmvcums.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tek.springmvcums.dao.UserRepository;
import com.tek.springmvcums.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User findById(long id) {
		return userRepo.findOne(id);
	}

	@Override
	public User findByName(String name) {
		return userRepo.findByUsername(name);
	}

	@Override
	public User saveOrUpdateUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public void deleteUserById(long id) {
		userRepo.delete(id);

	}

	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public void deleteAllUsers() {
		userRepo.deleteAll();
	}

	@Override
	public boolean isUserExist(User user) {
		return findByName(user.getUsername()) != null;
		 
	}

}
