package com.tek.springmvcums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tek.springmvcums.model.User;
import com.tek.springmvcums.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(users, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		User user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<User> save(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {

		System.out.println("Save User: "+user);
		if (userService.isUserExist(user)) {
			System.out.println("User is already exist");
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}

		User savedUser = userService.saveOrUpdateUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable("id") long id, @RequestBody User user) {

		User updateUser = userService.findById(id);

		if (updateUser == null) {
			System.out.println("User with id  " + id + "   not found in the list");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		updateUser.setUsername(user.getUsername());
		updateUser.setAddress(user.getAddress());
		updateUser.setEmail(user.getEmail());

		User updatedUser = userService.saveOrUpdateUser(updateUser);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> delete(@PathVariable("id") long id) {

		User deleteUser = userService.findById(id);

		if (deleteUser == null) {
			System.out.println("User with id " + id + " not found in the list");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.OK);
	}

	@DeleteMapping("/")
	public ResponseEntity<Void> deleteAll() {
		userService.deleteAllUsers();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
