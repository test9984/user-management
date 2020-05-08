package org.birlasoft.usermanagement.service;

import java.util.List;

import org.birlasoft.usermanagement.dto.WorkSpaceDto;


public interface WorkSpaceService {
	List<WorkSpaceDto> getAllWorkSpaces();

	WorkSpaceDto createWorkspace(WorkSpaceDto workSpaceDto);
	WorkSpaceDto deleteWorkspace(Integer workspaceId);
}
