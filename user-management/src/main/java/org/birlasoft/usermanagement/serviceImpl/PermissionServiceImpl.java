package org.birlasoft.usermanagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.birlasoft.usermanagement.bean.Permission;
import org.birlasoft.usermanagement.dto.PermissionDto;
import org.birlasoft.usermanagement.exception.ObjectNotFoundException;
import org.birlasoft.usermanagement.repositry.PermissionRepositry;
import org.birlasoft.usermanagement.service.CommonService;
import org.birlasoft.usermanagement.service.PermissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermissionServiceImpl implements PermissionService, CommonService<Permission, PermissionDto> {
	@Autowired
	private PermissionRepositry permissionRepositry;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<PermissionDto> getAllPermissons() {
		List<Permission> permissionList = permissionRepositry.findByDeleteStatusFalse();
		return permissionList.stream().map(this::convertToDto).collect(Collectors.toList());

	}

	@Override
	public PermissionDto convertToDto(Permission entity) {
		return modelMapper.map(entity, PermissionDto.class);
		
	}

	@Override
	public Permission convertToEntity(PermissionDto dto) throws ParseException {
		return  modelMapper.map(dto, Permission.class);
	}

	@Override
	public PermissionDto createPermission(PermissionDto permission) {
		return convertToDto(permissionRepositry.save(convertToEntity(permission)));
	}

	@Transactional(readOnly = true)
	@Override
	public Permission getPermission(Integer permissionId) {
		Permission permission = permissionRepositry.findByIdAndDeleteStatusFalse(permissionId)
				.orElseThrow(() -> new ObjectNotFoundException("Permission not found for this id :: " + permissionId));
		return permission;
	}
	
	@Override
	@Transactional
	public void deletePermission(Integer permissionId) {
		Permission permission =getPermission(permissionId);
		permission.setDeleteStatus(true);
		permissionRepositry.save(permission);
	}

}
