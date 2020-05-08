package org.birlasoft.usermanagement.service;

import java.util.List;

import org.birlasoft.usermanagement.dto.UserDto;
import org.birlasoft.usermanagement.dto.UserProfileDto;

public interface UserService {

	List<UserDto> getAllUsers();

	UserDto getUser(Integer userId);

	UserDto createUser(UserDto user);

	void deleteUser(Integer userId);

	Boolean isEmailAlreadyExist(String emailId);

	UserDto updateUser(UserProfileDto userprofile );

}
