package org.birlasoft.usermanagement.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class PasswordsHistoryDto {
	@NotBlank
	private Integer uid;
	@NotBlank
	private String currentPassword;
	@NotBlank
	private String newPassword;
	@NotBlank
	private String confirmPassword;
	
	@JsonIgnore
	private String dbPassword;
	@JsonIgnore
	private List<String>oldPwdList=new ArrayList<String>();
}
