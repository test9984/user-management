package org.birlasoft.usermanagement.controller;

import javax.validation.Valid;

import org.birlasoft.usermanagement.contants.CONSTANTS;
import org.birlasoft.usermanagement.dto.Status;
import org.birlasoft.usermanagement.dto.UserDto;
import org.birlasoft.usermanagement.dto.UserProfileDto;
import org.birlasoft.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@Api(value = "user api")
@RestController()
public class UserContoller {
	@Autowired
	private UserService userService;
	/*@Autowired
	private UserProfileValidator userProfileValidator;*/

	@ApiOperation(value = "View a list of available Users", response = Status.class)
	@GetMapping("/users")
	public ResponseEntity<Status> getUsers() {
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, userService.getAllUsers()));
	}

	@ApiOperation(value = "Get User by Id", response = Status.class)
	@GetMapping("/users/{id}")
	public ResponseEntity<Status> getUser(@PathVariable(value = "id") Integer userId) {
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, userService.getUser(userId)));

	}

	@ApiOperation(value = "Add a user", response = Status.class)
	@PostMapping("/users")
	public ResponseEntity<Status> saveUser(@RequestBody @Valid UserDto user) {
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, userService.createUser(user)));
	}

	@ApiOperation(value = "update a user", response = Status.class)
	@PutMapping("/users")
	public ResponseEntity<Status> updateUser(@RequestBody @Valid UserProfileDto user) {
		//userProfileValidator.isValidUserProfile(user);
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, userService.updateUser(user)));
	}

	@ApiOperation(value = "Delete user by id", response = Status.class)
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Status> DeleteUser(@PathVariable("id") Integer userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, userId));
	}

}
