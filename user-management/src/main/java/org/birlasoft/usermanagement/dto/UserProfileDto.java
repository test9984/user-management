package org.birlasoft.usermanagement.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "User")
public class UserProfileDto {
	private Integer id;

	@JsonIgnore
	private Boolean deleteStatus = Boolean.FALSE;
	@Size(max = 45, message = "{error.first.name.length}")
	@NotBlank(message = "{error.first.name}")
	private String firstName;

	@Size(max = 45, message = "{error.first.name.length}")
	@NotBlank(message = "{error.last.name}")
	private String lastName;

	@NotBlank(message = "{error.email}")
	@Email
	private String emailAddress;

	@NotBlank(message = "{error.password}")
	private String password;

	@NotBlank(message = "{error.organization.name}")
	private String organizationName;

	@NotBlank(message = "{error.country}")
	private String country;
	@NotBlank(message = "{error.question}")
	private String questionAnswer;
	@Size(max = 329384, message = "{error.photo}")
	private byte[] photo;
	private Boolean accountExpired = false;
	private Boolean accountLocked = false;
	private Boolean credentialExpired = false;
	private Boolean accountEnabled = false;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private Boolean firstLogin = false;
	private Boolean isEditable = false;
	@NotEmpty
	private Set<RoleDto> rolesList = new HashSet<RoleDto>();
	@NotEmpty
	private Set<WorkSpaceDto> workspacesList = new HashSet<WorkSpaceDto>();;

}
