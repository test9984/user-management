package org.birlasoft.usermanagement.validators;

import org.birlasoft.usermanagement.dto.UserDto;
import org.birlasoft.usermanagement.exception.ThirdeyeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class UserProfileValidator {
	@Autowired
    private  MessageSource messageSource;


	
	public boolean isValidOrganization(String organizationName) {
		return (organizationName != null && !organizationName.isEmpty());
	}

	public boolean isValidCountry(String country) {
		return (country != null && !country.isEmpty());
	}

	public boolean isValidQuestion(String question) {
		return (question != null && !question.isEmpty());
	}

	public boolean isValidUserProfile(UserDto userDto) {
		if (!isValidOrganization(userDto.getOrganizationName())) {
			throw new ThirdeyeException("error.country");
		} else if (!isValidQuestion(userDto.getQuestionAnswer())) {
			throw new ThirdeyeException("error.organization.name");
		} else if (!isValidCountry(userDto.getCountry())) {
			throw new ThirdeyeException("error.country");
		} else
			return true;

	}
}
