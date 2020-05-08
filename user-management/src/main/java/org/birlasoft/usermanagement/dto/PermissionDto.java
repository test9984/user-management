package org.birlasoft.usermanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Permission")
public class PermissionDto {
	private Integer id;
	private String permissionName;
	private String permissionDesc;
	@JsonIgnore
	private Boolean deleteStatus = Boolean.FALSE;
	
	public String getPermissionCatogery() {
		return permissionName.substring(0,permissionName.indexOf("_"));
	}
}
