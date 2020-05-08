package org.birlasoft.usermanagement.exception;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ThirdeyeExceptionResponce {
	private Date timeStamp;
	private Object message;
	private String details;

	

}
