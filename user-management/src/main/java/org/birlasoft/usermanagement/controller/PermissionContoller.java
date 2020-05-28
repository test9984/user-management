package org.birlasoft.usermanagement.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.birlasoft.usermanagement.contants.CONSTANTS;
import org.birlasoft.usermanagement.dto.PermissionDto;
import org.birlasoft.usermanagement.dto.Status;
import org.birlasoft.usermanagement.service.PermissionService;
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

@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "Permission")
@RestController()
public class PermissionContoller {
	@Autowired
	private PermissionService permissionService;

	@GetMapping("/permissions")
	@ApiOperation(value = "View a list of available Permission", response = Status.class)
	public ResponseEntity<Status> getPermissions() {
		
		 Map<String, List<PermissionDto>> permissionMap = permissionService.getAllPermissons().stream()
			.collect(Collectors.groupingBy(PermissionDto::getPermissionCatogery, Collectors.toList()

			));
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS,permissionMap));
	}

	@GetMapping("/permissions/{permmissionId}")
	@ApiOperation(value = "Delete Permission", response = Status.class)
	public ResponseEntity<Status> getPermission(@PathVariable("permmissionId") Integer permissionId) {
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, permissionService.getPermission(permissionId)));
	}

	@GetMapping("/permissions/category")
	@ApiOperation(value = " Get Permission category ", response = Status.class)
	public ResponseEntity<Status> getPermissionCategory() {
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, permissionService.getAllPermissons().stream()
				.collect(Collectors.groupingBy(PermissionDto::getPermissionCatogery, Collectors.counting()
				))));
	}

	@PostMapping("/permissions")
	@ApiOperation(value = "add new permission Permission", response = Status.class)
	public ResponseEntity<Status> createPermission(@RequestBody @Valid PermissionDto permission) {
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, permissionService.createPermission(permission)));
	}

	@DeleteMapping("/permissions/{permmissionId}")
	@ApiOperation(value = "Delete Permission", response = Status.class)
	public ResponseEntity<Status> createPermission(@PathVariable("permmissionId") Integer permissionId) {
		permissionService.deletePermission(permissionId);
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, "delete successfull"));
	}

}
