package org.birlasoft.usermanagement.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Permission")
public class PermissionDto {
	private Integer id;
	@NotBlank(message="${error.permission.permission.name}")
	private String permissionName;
	@NotBlank(message="${error.permission.permission.description}")
	private String permissionDesc;
	@JsonIgnore
	private Boolean deleteStatus = Boolean.FALSE;
	
	public String getPermissionCatogery() {
		return permissionName.substring(0,permissionName.indexOf("_"));
	}
}
