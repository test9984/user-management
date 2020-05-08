package org.birlasoft.usermanagement.service;

import java.util.List;

import org.birlasoft.usermanagement.bean.Role;
import org.birlasoft.usermanagement.dto.RoleDto;


public interface RoleService {

	List<RoleDto> getAllRoles();

	RoleDto getRole(Integer roleId);

	RoleDto createRole(RoleDto role);

	Role deleteRole(Integer roleId);

	RoleDto updateRole(RoleDto role);

	Boolean isRoleAlreadyExists(String roleName);

}
