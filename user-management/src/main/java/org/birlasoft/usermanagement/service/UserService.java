package org.birlasoft.usermanagement.service;

import java.util.List;

import org.birlasoft.usermanagement.bean.User;
import org.birlasoft.usermanagement.dto.UserDto;

public interface UserService {

	List<UserDto> getAllUsers();

	List<User> findAll();

	UserDto getUser(Integer userId);

	UserDto createUser(UserDto user);

	void deleteUser(Integer userId);

	Boolean isEmailAlreadyExist(String emailId);

	void updatePassword(Integer id, String password);

	UserDto updateUser(UserDto user);
	UserDto updateUserProfile(UserDto user);


}
