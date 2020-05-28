package org.birlasoft.usermanagement.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.birlasoft.usermanagement.bean.PasswordsHistory;
import org.birlasoft.usermanagement.contants.CONSTANTS;
import org.birlasoft.usermanagement.contants.MESSAGE_TYPES;
import org.birlasoft.usermanagement.dto.PasswordsHistoryDto;
import org.birlasoft.usermanagement.dto.Status;
import org.birlasoft.usermanagement.dto.UserDto;
import org.birlasoft.usermanagement.repositry.PasswordsHistoryRepository;
import org.birlasoft.usermanagement.service.MessageService;
import org.birlasoft.usermanagement.service.UserService;
import org.birlasoft.usermanagement.validators.ChangePasswordValidator;
import org.birlasoft.usermanagement.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;

@Log
@CrossOrigin
@Api(value = "user api")
@RestController()
public class UserContoller {
	@Autowired
	private UserService userService;
	@Autowired
	private UserValidator userValidator;
	@Autowired
	private ChangePasswordValidator changePasswordValidator;

	@Autowired
	private MessageService messageService;
	@Autowired
	private PasswordsHistoryRepository passwordsHistoryRepository;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

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
	@PutMapping(value="/users" ,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> updateUser(@RequestBody @Valid UserDto user,
			@RequestParam(value = "fileUpload", required = false) MultipartFile fileUpload, BindingResult result)
			throws IOException {
		Status status = new Status();
		if (user.getIsUserProfile()) {
			userValidator.validateUserProfile(user, result, fileUpload, null);
			if (result.hasErrors()) {

				status.setIsSuccess(CONSTANTS.FAIL);
				status.setMessageType(MESSAGE_TYPES.ERROR);
				status.setMessage(messageService.getErrorJson(result));
				return ResponseEntity.ok(status);
			} else {
				status.setIsSuccess(CONSTANTS.SUCCESS);
				status.setMessageType(MESSAGE_TYPES.INFO);
				setUserProfilePhoto(user, fileUpload);
				status.setData(userService.updateUserProfile(user));
				return ResponseEntity.ok(status);
			}
		}

		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, userService.updateUser(user)));
	}

	@ApiOperation(value = "Delete user by id", response = Status.class)
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Status> DeleteUser(@PathVariable("id") Integer userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, userId));
	}

	@ApiOperation(value = "changePassword a user profile", response = Status.class)
	@PostMapping("/users/changePassword")
	public ResponseEntity<Status> changeUserPassword(@RequestBody PasswordsHistoryDto passwordsHistoryDto,
			BindingResult result) throws Exception {
		userService.updatePassword(1, "change api call");
		UserDto userDb = userService.getUser(passwordsHistoryDto.getUid());
		passwordsHistoryDto.setDbPassword(userDb.getPassword());
		List<PasswordsHistory> passwordsHistoryList = passwordsHistoryRepository
				.findTop3ByUidOrderByCreatedDateDesc(passwordsHistoryDto.getUid());
		log.info("password history   ::" + passwordsHistoryList.size());
		if (passwordsHistoryList != null && passwordsHistoryList.size() > 0)
			passwordsHistoryList.stream().forEach(
					passwordsHistory -> passwordsHistoryDto.getOldPwdList().add(passwordsHistory.getPassword()));
		changePasswordValidator.validate(passwordsHistoryDto, result);

		if (result.hasErrors()) {
			Status status = new Status();
			status.setIsSuccess(CONSTANTS.FAIL);
			status.setMessageType(MESSAGE_TYPES.ERROR);
			status.setMessage(messageService.getErrorJson(result));
			return ResponseEntity.ok(status);
		} else {
			userService.updatePassword(passwordsHistoryDto.getUid(), passwordsHistoryDto.getNewPassword());
			return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, "password update successfull"));
		}

	}

	private void setUserProfilePhoto(UserDto userToUpdate, MultipartFile fileUpload) throws IOException {
		if (!fileUpload.isEmpty() && fileUpload.getSize() != 0) {
			byte[] bytes = fileUpload.getBytes();
			userToUpdate.setPhoto(bytes);
		} else if (userToUpdate.getPhoto().length == 0) {
			userToUpdate.setPhoto(null);
		}
	}

}
