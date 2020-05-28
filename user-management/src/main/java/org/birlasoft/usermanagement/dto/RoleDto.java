package org.birlasoft.usermanagement.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Role")
public class RoleDto {
	private Integer id;
	@NotBlank(message= "${error.userRole.title}")
	private String roleName;
	@NotBlank(message="${error.userRole.description}")
	private String roleDescription;
	@JsonIgnore
	private Boolean deleteStatus = Boolean.FALSE;
	private Set<PermissionDto> permissionsList = new HashSet<>();
}
