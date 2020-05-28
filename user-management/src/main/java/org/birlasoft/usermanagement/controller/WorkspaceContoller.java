package org.birlasoft.usermanagement.controller;

import javax.validation.Valid;

import org.birlasoft.usermanagement.contants.CONSTANTS;
import org.birlasoft.usermanagement.dto.Status;
import org.birlasoft.usermanagement.dto.WorkSpaceDto;
import org.birlasoft.usermanagement.service.WorkSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@Api(value = "Workspaces")
@RestController()
public class WorkspaceContoller {
	@Autowired
	private WorkSpaceService workSpaceService;

	@GetMapping("/workspaces")
	@ApiOperation(value = "View a list of available workspace", response = Status.class)
	public ResponseEntity<Status> getWorkspace() {
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, workSpaceService.getAllWorkSpaces()));
	}

	@PostMapping("/workspaces")
	@ApiOperation(value = "Create new workspace", response = Status.class)
	public ResponseEntity<Status> createWorkspace(@RequestBody @Valid WorkSpaceDto workSpaceDto) {
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, workSpaceService.createWorkspace(workSpaceDto)));
	}

	@DeleteMapping("/workspaces/{worksapceId}")
	@ApiOperation(value = "delete  workspace", response = Status.class)
	public ResponseEntity<Status> deleteWorkspace(@PathVariable("worksapceId") Integer worksapceId) {
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, workSpaceService.deleteWorkspace(worksapceId)));
	}
}
