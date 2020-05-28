package org.birlasoft.usermanagement.validators;


import org.birlasoft.usermanagement.constant.Constants;
import org.birlasoft.usermanagement.dto.PasswordsHistoryDto;
import org.birlasoft.usermanagement.serviceImpl.MessagesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This {@code class} validate the {@code necessary}  fields ,
 * logic of {@code validation} is here.
 */
@Component
public class ChangePasswordValidator implements Validator {
	

	private static final String CURRENT_USER_PASS = "currentPassword";
	private static final String NEW_USER_PASS = "newPassword";
	private static final String CONFIRM_USER_PASS = "confirmPassword";

	@Autowired
	private MessagesServiceImpl messages;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PasswordsHistoryDto.class.equals(clazz);
	}
	
	@Override
    public void validate(Object target, Errors errors) {
		PasswordsHistoryDto password = (PasswordsHistoryDto) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, CURRENT_USER_PASS, "error.currentPassword", messages.get("error.currentPassword"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, NEW_USER_PASS, "error.newPassword", messages.get("error.newPassword"));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, CONFIRM_USER_PASS, "error.confirmPassword", messages.get("error.confirmPassword"));

		if (!password.getNewPassword().matches(Constants.PASSWORD_POLICY)) {
			errors.rejectValue(NEW_USER_PASS, "error.newPassword.policy", messages.get("error.newPassword.policy"));
		}

		if (!password.getConfirmPassword().matches(Constants.PASSWORD_POLICY))
			errors.rejectValue(CONFIRM_USER_PASS, "error.confirmPassword.policy", messages.get("error.confirmPassword.policy"));


		//new & confirm password
		if (!password.getNewPassword().equals(password.getConfirmPassword())) {
			errors.rejectValue(CONFIRM_USER_PASS, "error.confirmPassword.mismatch", messages.get("error.confirmPassword.mismatch"));
		}
		//current & db password
		/*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (!passwordEncoder.matches(password.getCurrentPassword(), password.getDbPassword())) {

			errors.rejectValue(CURRENT_USER_PASS, "error.currentPassword.mismatch", messages.get("error.currentPassword.mismatch"));
		}*/
		// current & new password
		if (password.getCurrentPassword().equals(password.getNewPassword())) {
			errors.rejectValue(NEW_USER_PASS, "error.Password.mismatch", messages.get("error.Password.mismatch"));
		}

		/*if (password.getOldPwdList().stream().anyMatch(s -> passwordEncoder.matches(password.getNewPassword(),s))) {
			errors.rejectValue(NEW_USER_PASS, "error.Password.mismatch", messages.get("error.Password.mismatch"));
		}*/
	}	
}
