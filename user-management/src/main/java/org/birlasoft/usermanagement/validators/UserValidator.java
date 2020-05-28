package org.birlasoft.usermanagement.validators;

import java.time.LocalDate;
import java.util.List;

import org.birlasoft.usermanagement.bean.Role;
import org.birlasoft.usermanagement.bean.User;
import org.birlasoft.usermanagement.constant.Constants;
import org.birlasoft.usermanagement.dto.UserDto;
import org.birlasoft.usermanagement.service.UserService;
import org.birlasoft.usermanagement.serviceImpl.MessagesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

/**
 * This {@code class} validate the {@code necessary}  fields ,
 * logic of {@code validation} is here.
 */
@Component
public class UserValidator implements Validator {
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String EMAIL = "emailAddress";
	private static final String USER_PASS = "password";
	private static final String ORGANIZATION_NAME ="organizationName";
	private static final String PHOTO = "photo";
	private static final String COUNTRY ="country";
	private static final String QUESTIONANSWER ="questionAnswer";
	private static final String ROLE ="roleName";

	private static final int PWD_EXPIRED_DAYS = 90;


	@Autowired
	private MessagesServiceImpl messages;
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDto.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		UserDto user = (UserDto)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIRST_NAME, "error.first.name", messages.get("error.first.name"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, LAST_NAME, "error.last.name", messages.get("error.last.name"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, EMAIL, "error.email", messages.get("error.email"));
		
		
		if(user.getWorkspacesList().isEmpty() && user.getId()==null && !user.getIsEditable()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, USER_PASS, "error.password", messages.get("error.password"));
		}

		if(!user.getPassword().matches(Constants.PASSWORD_POLICY) && !user.getIsEditable()){
            errors.rejectValue(USER_PASS, "error.confirmPassword.policy", messages.get("error.confirmPassword.policy"));
		}

		List<User> userall = userService.findAll();
		for (User user2 : userall) {
			if(user.getId() !=null && user.getId().equals(user2.getId()) && user.getDeleteStatus()){
				break;
			}else if(user.getEmailAddress() !=null && user.getEmailAddress().equalsIgnoreCase(user2.getEmailAddress())){
				if(user2.getDeleteStatus()){
					errors.rejectValue(EMAIL, "error.email.deactive", messages.get("error.email.deactive "));
				}else if(user.getId() == null){
				   errors.rejectValue(EMAIL, "error.email.exists", messages.get("error.email.exists")); 
				}
			}
		}
		if(user.getFirstName()!= null && user.getFirstName().length() > 45){
			
			 errors.rejectValue(FIRST_NAME, "error.first.name.length", messages.get("error.first.name.length")); 
		 }
		if(user.getLastName()!= null && user.getLastName().length() > 45){
			
			 errors.rejectValue(LAST_NAME, "error.last.name.length", messages.get("error.last.name.length")); 
		 }
		
	}

	public void validateUserProfile(Object target, Errors errors,MultipartFile fileUpload,List<String> listOfQuestionAnswer){
		UserDto user = (UserDto)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIRST_NAME, "error.first.name", messages.get("error.first.name"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, LAST_NAME, "error.last.name", messages.get("error.last.name"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, EMAIL, "error.email", messages.get("error.email"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, ORGANIZATION_NAME, "error.organization.name", messages.get("error.organization.name"));
		if(fileUpload!=null&&fileUpload.getSize()>329384){
			errors.rejectValue(PHOTO, "error.photo", messages.get("error.photo"));
		}
		if(user.getCountry()!=null && "select".equalsIgnoreCase(user.getCountry())){
			errors.rejectValue(COUNTRY, "error.country", messages.get("error.country"));
		}
		
		if("-1".equals(listOfQuestionAnswer.get(0)) || "-1".equals(listOfQuestionAnswer.get(2))){
			errors.rejectValue(QUESTIONANSWER, "error.question", messages.get("error.question"));
		}	
	}
	
	
	public void validateUserRoleModel(Role role,Errors errors) {
		
		if (role.getRoleName() != null && role.getRoleName().isEmpty()) {			  
			  ValidationUtils.rejectIfEmptyOrWhitespace(errors, ROLE, "error.userRole.title", messages.get("error.userRole.title "));
		}
	}

	public boolean isPasswordExpired(User user) {
		boolean check = false;
		LocalDate lastUpdate;
		String lastDate = user.getUpdatedDate().toString();
		if (lastDate.contains("."))
			lastUpdate = LocalDate.parse(lastDate.substring(0, lastDate.indexOf('.')));
		 else
			lastUpdate = LocalDate.parse(lastDate);

		lastUpdate = lastUpdate.plusDays(PWD_EXPIRED_DAYS);

		LocalDate today = LocalDate.now();

		if (lastUpdate.compareTo(today) < 0)
			check = true;
		return check;

	}
}
