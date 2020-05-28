package org.birlasoft.usermanagement.dto;

import java.io.Serializable;

import org.birlasoft.usermanagement.contants.CONSTANTS;
import org.birlasoft.usermanagement.contants.MESSAGE_TYPES;

import lombok.Data;
@Data
public class Status implements Serializable {
	private static final long serialVersionUID = 234234523211L;
	private Boolean isSuccess = true;
	private String messageType = MESSAGE_TYPES.INFO;
	private Object message = CONSTANTS.BLANK;
	private Object data;

	public Status() {
		super();
		this.isSuccess = true;
	}

	public Status(Boolean isSuccess, Object data) {
		super();
		this.isSuccess = isSuccess;
		this.data = data;
	}

	public Status(Boolean isSuccess, String messageType, String message, Object data) {
		super();
		this.isSuccess = isSuccess;
		this.messageType = messageType;
		this.message = message;
		this.data = data;
	}

	public Status(String message, String messageType) {
		super();
		this.isSuccess = false;
		this.messageType = messageType;
		this.message = message;
	}



}
