package org.birlasoft.usermanagement.controller;

import javax.validation.Valid;

import org.birlasoft.usermanagement.contants.CONSTANTS;
import org.birlasoft.usermanagement.dto.RoleDto;
import org.birlasoft.usermanagement.dto.Status;
import org.birlasoft.usermanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin
@Api(value = "Role Api")
@RestController()
public class RoleContoller {
	@Autowired
	private RoleService roleService;

	@ApiOperation(value = "View a list of available Roles", response = Status.class)
	@GetMapping("/roles")
	public ResponseEntity<Status> getRoles() {
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, roleService.getAllRoles()));
	}

	@ApiOperation(value = "Get Role by Id", response = Status.class)
	@GetMapping("roles/{id}")
	public ResponseEntity<Status> getRole(@PathVariable(value = "id") Integer roleId) {
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, roleService.getRole(roleId)));
	}

	@ApiOperation(value = "add new role", response = Status.class)
	@PostMapping("/roles")
	public ResponseEntity<Status> saveRole(@RequestBody @Valid RoleDto role) {
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, roleService.createRole(role)));
	}

	@ApiOperation(value = "update role", response = Status.class)
	@PutMapping("/roles")
	public ResponseEntity<Status> updateRole(@RequestBody RoleDto role) {
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, roleService.updateRole(role)));
	}

	@ApiOperation(value = "delete role by id", response = Status.class)
	@DeleteMapping("/roles/{roleId}")
	public ResponseEntity<Status> deleteRole(@PathVariable("roleId") Integer roleId) {
		return ResponseEntity.ok(new Status(CONSTANTS.SUCCESS, roleService.deleteRole(roleId)));
	}

}
