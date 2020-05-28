package org.birlasoft.usermanagement.serviceImpl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.birlasoft.usermanagement.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class MessagesServiceImpl implements MessageService{

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }

    public String get(String code) {
        return accessor.getMessage(code);
    }
    
    public Map<String,String> getErrorJson(BindingResult result) throws JsonProcessingException {
    	Map<String,String> errorMap=new HashMap<String,String>();
    	result.getFieldErrors()
    	.stream()
    	.forEach(fieldError -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
    return	errorMap;
    }

}