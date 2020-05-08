package org.birlasoft.usermanagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.birlasoft.usermanagement.bean.WorkSpace;
import org.birlasoft.usermanagement.dto.WorkSpaceDto;
import org.birlasoft.usermanagement.exception.ObjectNotFoundException;
import org.birlasoft.usermanagement.repositry.WorkSpaceRepositry;
import org.birlasoft.usermanagement.service.CommonService;
import org.birlasoft.usermanagement.service.WorkSpaceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WokSpaceServiceImpl implements WorkSpaceService, CommonService<WorkSpace, WorkSpaceDto> {
	@Autowired
	private WorkSpaceRepositry workSpaceRepositry;
	@Autowired
	private ModelMapper modelMapper;

	@Transactional(readOnly = true)
	@Override
	public List<WorkSpaceDto> getAllWorkSpaces() {
		return workSpaceRepositry.findByDeleteStatusFalse().stream().map(this::convertToDto)
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public WorkSpaceDto createWorkspace(WorkSpaceDto workSpaceDto) {
		return convertToDto(workSpaceRepositry.save(convertToEntity(workSpaceDto)));
	}

	@Override
	public WorkSpaceDto convertToDto(WorkSpace entity) {
		return modelMapper.map(entity, WorkSpaceDto.class);
	}

	@Override
	public WorkSpace convertToEntity(WorkSpaceDto dto) throws ParseException {
		return modelMapper.map(dto, WorkSpace.class);
	}

	@Transactional
	@Override
	public WorkSpaceDto deleteWorkspace(Integer workspaceId) {
		WorkSpace workSpace = workSpaceRepositry.findByIdAndDeleteStatusFalse(workspaceId)
				.orElseThrow(() -> new ObjectNotFoundException("worksapce not found for this id :: " + workspaceId));
		workSpace.setDeleteStatus(true);
		return convertToDto(workSpaceRepositry.save(workSpace));

	}

}
