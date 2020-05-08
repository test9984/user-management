package org.birlasoft.usermanagement.service;

import org.springframework.expression.ParseException;

public interface CommonService<Entity,Dto> {
	Dto convertToDto(Entity entity) ;
	Entity convertToEntity(Dto dto) throws ParseException ;
}
