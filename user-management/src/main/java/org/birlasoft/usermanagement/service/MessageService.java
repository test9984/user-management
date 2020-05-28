package org.birlasoft.usermanagement.service;

import java.util.Map;

import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageService {
	 public Map<String,String> getErrorJson(BindingResult result)  throws JsonProcessingException;
}
