package org.birlasoft.usermanagement.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
public class WorkSpaceDto {
	private Integer id;
	private String workspaceName;
	private String workspaceDesc;
	@JsonIgnore
	private Boolean deleteStatus =Boolean.FALSE;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
}
