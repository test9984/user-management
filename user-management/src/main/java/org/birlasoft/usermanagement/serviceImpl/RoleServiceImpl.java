package org.birlasoft.usermanagement.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.birlasoft.usermanagement.bean.Permission;
import org.birlasoft.usermanagement.bean.Role;
import org.birlasoft.usermanagement.dto.PermissionDto;
import org.birlasoft.usermanagement.dto.RoleDto;
import org.birlasoft.usermanagement.exception.ObjectAlreadyExistException;
import org.birlasoft.usermanagement.exception.ObjectNotFoundException;
import org.birlasoft.usermanagement.repositry.PermissionRepositry;
import org.birlasoft.usermanagement.repositry.RoleRepositry;
import org.birlasoft.usermanagement.service.CommonService;
import org.birlasoft.usermanagement.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService, CommonService<Role, RoleDto> {
	@Autowired
	private RoleRepositry roleRepositry;

	@Autowired
	private PermissionRepositry permissionRepositry;
	@Autowired
	private ModelMapper modelMapper;

	@Transactional(readOnly = true)
	@Override
	public List<RoleDto> getAllRoles() {
		List<Role> roleList = roleRepositry.findByDeleteStatusFalse();
		return roleList.stream().map((role) -> convertToDto(role, false)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	@Override
	public RoleDto getRole(Integer roleId) {
		Role role = roleRepositry.findByIdAndDeleteStatusFalse(roleId)
				.orElseThrow(() -> new ObjectNotFoundException("Role not found for this id :: " + roleId));
		Set<Permission> permissions = role.getPermissions();
		log.info("permission of ={} role  id = {}", permissions, roleId);
		return convertToDto(role);
	}

	@Transactional(readOnly = true)

	public Role getRoleEntity(Integer roleId) {
		Role role = roleRepositry.findByIdAndDeleteStatusFalse(roleId)
				.orElseThrow(() -> new ObjectNotFoundException("Role not found for this id :: " + roleId));
		Set<Permission> permissions = role.getPermissions();
		log.info("permission of ={} role  id = {}", permissions, roleId);
		return role;
	}

	@Transactional
	@Override
	public RoleDto createRole(RoleDto role) {
		if (isRoleAlreadyExists(role.getRoleName()))
			throw new ObjectAlreadyExistException("Role Name ::" + role.getRoleName() + "already exists");
		return convertToDto(roleRepositry.save(convertToEntity(role)));
	}

	@Transactional
	@Override
	public RoleDto updateRole(RoleDto role) {
		roleRepositry.findByIdAndDeleteStatusFalse(role.getId())
				.orElseThrow(() -> new ObjectNotFoundException("Role not found for this id :: " + role.getId()));
		return convertToDto(roleRepositry.save(convertToEntity(role)));
	}

	@Transactional(readOnly = true)
	@Override
	public Boolean isRoleAlreadyExists(String roleName) {
		return roleRepositry.findByRoleNameAndDeleteStatusFalse(roleName).size() != 0;
	}

	@Transactional
	@Override
	public Role deleteRole(Integer roleId) {
		Role role = getRoleEntity(roleId);
		role.setDeleteStatus(true);
		return roleRepositry.save(role);
	}

	@Override
	public RoleDto convertToDto(Role entity) {

		RoleDto dto = modelMapper.map(entity, RoleDto.class);
		Set<PermissionDto> permissions = dto.getPermissionsList();
		entity.getPermissions().forEach((permission -> {
			permissions.add(modelMapper.map(permission, PermissionDto.class));
		}));
		dto.setPermissionsList(permissions);

		return dto;
	}

	private RoleDto convertToDto(Role entity, Boolean isExclude) {
		RoleDto dto = modelMapper.map(entity, RoleDto.class);
		if (isExclude) {
			Set<PermissionDto> permissions = dto.getPermissionsList();
			entity.getPermissions().forEach((permission -> {
				permissions.add(modelMapper.map(permissions, PermissionDto.class));

			}));
			dto.setPermissionsList(permissions);
		}
		return dto;
	}

	@Transactional(readOnly = true)
	@Override
	public Role convertToEntity(RoleDto dto) throws ParseException {
		Role role = modelMapper.map(dto, Role.class);
		Set<Permission> permissions = new HashSet<>();
log.info("{}",dto.getPermissionsList().size());
		dto.getPermissionsList().forEach((v) -> {
			Permission permission = permissionRepositry.findByIdAndDeleteStatusFalse(v.getId()).get();
			permissions.add(permission);
			
		});
		role.setPermissions(permissions);
		return role;
	}
}
