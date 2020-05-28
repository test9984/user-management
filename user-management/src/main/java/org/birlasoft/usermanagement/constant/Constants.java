package org.birlasoft.usermanagement.constant;
import java.math.BigDecimal;

/**
 * Interface for declare {@code constants} 
 */
public interface Constants {
	
	public static final String ELASTICSEARCH_HOST_NAME = "elastic.search.host";
	public static final String ELASTICSEARCH_IP_ADDRESS = "elastic.search.ip.address";
	
	public static final String REQUEST_PARAM_CSRF = "_csrf";
	// Default value of weight in parameter function
	public static final BigDecimal DEFAULT_WEIGHT_VALUE = new BigDecimal(1);
	
	// Default value of CONSTANT in parameter function
	public static final int DEFAULT_CONSTANT_VALUE = 1;
	
	// Constants for delete status in database
	public static final Boolean DELETE_STATUS_FALSE = false;
	public static final Boolean DELETE_STATUS_TRUE = true;
	
	// Constants for account expired in database
	public static final Boolean ACCOUNT_EXPIRED_FALSE = false;
	public static final Boolean ACCOUNT_EXPIRED_TRUE = true;
	
	// Constants for account locked in database
	public static final Boolean ACCOUNT_LOCKED_FALSE = false;
	public static final Boolean ACCOUNT_LOCKED_TRUE = true;
	
	//Constant TEXT for Other option in MCQ
	public static final String OTHER_TEXT = "Other";
	
	//Default values for Question Type.
	public static final int DEFAULT_NUMBER_MIN = 0;
	
	//Empty String
	public static final String EMPTY_STRING = "";
	
	// Bcm Level
	public static final String BCM_LEVEL_ADD = "add";
	public static final String BCM_LEVEL_EDIT = "edit";
	
	// Title for Add BCM Levels
	public static final String BCM_LEVEL_TITLE_VALUE_CHAIN = "Add Value Chain";
	public static final String BCM_LEVEL_TITLE_BUSINESS_FUNCTION = "Add Business Function";
	public static final String BCM_LEVEL_TITLE_BUSINESS_PROCESS = "Add Business Process";
	public static final String BCM_LEVEL_TITLE_ACTIVITY = "Add Activity";
	public static final String BCM_LEVEL_TITLE_SUB_BUSINESS_PROCESS= "Add Sub Business Process";
	
	// Title for Edit BCM Levels
	public static final String BCM_LEVEL_TITLE_EDIT_VALUE_CHAIN = "Edit Value Chain";
	public static final String BCM_LEVEL_TITLE_EDIT_BUSINESS_FUNCTION = "Edit Business Function";
	public static final String BCM_LEVEL_TITLE_EDIT_BUSINESS_PROCESS = "Edit Business Process";
	public static final String BCM_LEVEL_TITLE_EDIT_ACTIVITY = "Edit Activity";
	public static final String BCM_LEVEL_TITLE_EDIT_SUB_BUSINESS_PROCESS = "Edit Sub Business Process";
	
	//Default column name for asset template column
	public static final String DEFAULT_COLUMN_NAME = "Name";
	public static final int DEFAULT_COLUMN_LENGTH = 200;
	public static final String DEFAULT_COUNTRY_NAME_COLUMN = "Country";
	public static final String DEFAULT_NOOFUSERS_COLUMN = "No Of Users";
	public static final String DEFAULT_LIFE_CYCLE = "Life Cycle Stage";
	public static final String JSON_DATA_TYPE = "JSON";
	
	public static final String __SYS_TCO = "__SYS_TCO";
	public static final String  PAGE_TITLE = "pageTitle";
	
	public static final String BASESEARCHURL = "thirdeye-search-web/rest/search";
	
	// Default value of asset color 
	public static final String DEFAULT_COLOR = "#99ddff";
	public static final String DEFAULT_COLOR_DESCRIPTION = "Default Color Scheme";
	
	// TEXT_FORMAT for Excel
	public static final String TEXT_FORMAT="@";
	
	// NA_DOUBLE constant
	public static final Double NA_DOUBLE = -1d;
	
	public static final String TEMPLATE_ID = "templateId";
	public static final String PARENT_ASSET = "parentAsset";
	public static final String CHILD_ASSET = "childAsset";
	public static final Boolean ACTIVE_STATUS = true;

	String PASSWORD_POLICY = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
}
