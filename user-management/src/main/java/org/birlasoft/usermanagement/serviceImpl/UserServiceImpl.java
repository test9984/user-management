package org.birlasoft.usermanagement.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.birlasoft.usermanagement.bean.Role;
import org.birlasoft.usermanagement.bean.User;
import org.birlasoft.usermanagement.bean.WorkSpace;
import org.birlasoft.usermanagement.dto.RoleDto;
import org.birlasoft.usermanagement.dto.UserDto;
import org.birlasoft.usermanagement.dto.UserProfileDto;
import org.birlasoft.usermanagement.dto.WorkSpaceDto;
import org.birlasoft.usermanagement.exception.ObjectAlreadyExistException;
import org.birlasoft.usermanagement.exception.ObjectNotFoundException;
import org.birlasoft.usermanagement.repositry.RoleRepositry;
import org.birlasoft.usermanagement.repositry.UserRepositry;
import org.birlasoft.usermanagement.repositry.WorkSpaceRepositry;
import org.birlasoft.usermanagement.service.CommonService;
import org.birlasoft.usermanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService, CommonService<User, UserDto> {
	@Autowired
	private UserRepositry userRepositry;

	@Autowired
	private RoleRepositry roleRepositry;
	@Autowired
	private WorkSpaceRepositry workSpaceRepositry;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional(readOnly = true)
	@Override
	public List<UserDto> getAllUsers() {
		List<User> userList = userRepositry.findByDeleteStatusFalse();
		return userList.stream().map((user)->convertToDto(user,false)).collect(Collectors.toList());

	}

	@Override
	@Transactional(readOnly = true)
	public UserDto getUser(Integer userId) {
		User user = userRepositry.findByIdAndDeleteStatusFalse(userId)
				.orElseThrow(() -> new ObjectNotFoundException("User not found for this id :: " + userId));
		Set<WorkSpace> workspaces = user.getWorkspaces();
		Set<Role> roles = user.getRoles();
		log.info("workspaces ::" + workspaces);
		log.info("roles ::" + roles);
		return convertToDto(user);
	}

	@Override
	@Transactional
	public UserDto createUser(UserDto user) {
		if (isEmailAlreadyExist(user.getEmailAddress()))
			throw new ObjectAlreadyExistException("this email id is already exists");
		User user2 = convertToEntity(user);
	//	setPassword(user2);
		return convertToDto(userRepositry.save(user2));
	}

	@Transactional
	@Override
	public UserDto updateUser(UserProfileDto userprofile) {
		UserDto user=modelMapper.map(userprofile,UserDto.class);
		userRepositry.findByIdAndDeleteStatusFalse(user.getId())
				.orElseThrow(() -> new ObjectNotFoundException("User not found for this id :: " + user.getId()));
		return convertToDto(userRepositry.save(convertToEntity(user)));
	}

	@Transactional(readOnly = true)
	@Override
	public Boolean isEmailAlreadyExist(String emailId) {
		return userRepositry.findAllByEmailAddressAndDeleteStatusFalse(emailId).size() != 0;
	}
	/*private void setPassword(User user) {
		if (user.getPassword() != null && !user.getPassword().isEmpty()) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(hashedPassword);
		}
	}
*/
	@Transactional
	@Override
	public void deleteUser(Integer userId) {
		User user = userRepositry.findByIdAndDeleteStatusFalse(userId)
				.orElseThrow(() -> new ObjectNotFoundException("EmpUserloyee not found for this id :: " + userId));
		user.setDeleteStatus(true);
		userRepositry.save(user);

	}

	@Override
	public UserDto convertToDto(User entity) {
		UserDto userDto = modelMapper.map(entity, UserDto.class);
		Set<RoleDto> roleIds = userDto.getRolesList();
		entity.getRoles().forEach((role) -> roleIds.add(modelMapper.map(role, RoleDto.class)));
		Set<WorkSpaceDto> workspaceIds = userDto.getWorkspacesList();
		entity.getWorkspaces().stream().forEach((workspace) -> workspaceIds.add(modelMapper.map(workspace, WorkSpaceDto.class)));

		return userDto;
	}

	private UserDto convertToDto(User entity, Boolean isExcludeSub) {
		UserDto userDto = modelMapper.map(entity, UserDto.class);
		if (isExcludeSub) {
			Set<RoleDto> roles = userDto.getRolesList();
			entity.getRoles().forEach((role) -> roles.add(modelMapper.map(role, RoleDto.class)));
			Set<WorkSpaceDto> workspaceIds = userDto.getWorkspacesList();
			entity.getWorkspaces().stream().forEach((workspace) -> workspaceIds.add(modelMapper.map(workspace, WorkSpaceDto.class)));
		}
		return userDto;
	}

	@Override
	@Transactional()
	public User convertToEntity(UserDto dto) throws ParseException {
		User user = modelMapper.map(dto, User.class);
		Set<Role> roles = new HashSet<Role>();
		Set<WorkSpace> workspaces = new HashSet<WorkSpace>();
		dto.getRolesList().stream().forEach((id) -> roles.add(roleRepositry.findByIdAndDeleteStatusFalse(id.getId()).get()));
		dto.getWorkspacesList().stream()
				.forEach((id) -> workspaces.add(workSpaceRepositry.findByIdAndDeleteStatusFalse(id.getId()).get()));
		user.setRoles(roles);
		user.setWorkspaces(workspaces);
		return user;
	}

}
