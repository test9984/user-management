package org.birlasoft.usermanagement.service;

import java.util.List;

import org.birlasoft.usermanagement.bean.Permission;
import org.birlasoft.usermanagement.dto.PermissionDto;

public interface PermissionService {

	List<PermissionDto> getAllPermissons();

	PermissionDto createPermission(PermissionDto permission);

	void deletePermission(Integer permissionId);

	Permission getPermission(Integer permissionId);

}
