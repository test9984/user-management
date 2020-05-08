package org.birlasoft.usermanagement.contants;

import java.text.SimpleDateFormat;
import java.util.Locale;

public interface CONSTANTS {

	String BLANK = "";
	String WHITE_SPACE = " ";
	Boolean SUCCESS = true;
	Boolean FAIL = false;
	SimpleDateFormat INPUT_DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	SimpleDateFormat OUTPUT_DATEFORMAT = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
	String LOGGER_MSG = "inside method  ->>  {}";
}
