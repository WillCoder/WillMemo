package com.willmemo;

public class memoconstant {

	static final int IMPORTANCENUM = 7;
//	String importenceLevel[];
//	importenceLevel[0]=new String("");
	static final String DBPATH = "/data/data/com.willmemo/files";
	static final String SAVEDIRNAME = "memoes";
//	static final String x = getFilesDir();
	static final String DBNAME = "willmemo.db";
	
	static final String SHARED_PREFERENCES_TIME_SET_HOUR = "willmemo_time_set_hour";
	static final String SHARED_PREFERENCES_TIME_SET_MINUTE = "willmemo_time_set_minute";
	static final String SHARED_PREFERENCES_TIME_UP_NOTI = "willmemo_time_up_noti";
	static final String SHARED_PREFERENCES_NAME = "willmemo_shared_preferences_name";
	static final String SHARED_PREFERENCES_START_GUIDE = "willmemo_shared_start_guide";
	
	static final String STATEINSERT = "memoinsert";
	static final String STATEUPDATE = "memoupdate";
	static final String STATEDELETE = "memodelete";
	
	static final String SERVICE_ACTION_UPDATE = "will.memo.ALARM_SERVICE_UPDATE";
	static final String SERVICE_ACTION_TIMEUP = "will.memo.ALARM_SERVICE_TIMEUP";
	
	static final int BTN_DELETE 					= 0;
	static final int BTN_ADD 						= 1;
	static final int BTN_DEFAULT 					= 2;
	
	static final int RANK_RANDOM 					= 3;
	static final int RANK_DEFAULT 					= 4;
	static final int RANK_OVERDUE_HIDE 				= 5;
	static final int RANK_OVERDUE_DISPLAY_TOP 		= 6;
	static final int RANK_OVERDUE_DISPLAY_BOTTOM 	= 7;
//	static final String STATEDELETE = "memodelete";
	
	
	static String importenceLevel[]={
		"#CCCCCC",
		"#FF0000",
		"#FFA500",
		"#FFCC00",
		"#CCCC00",
		"#99CC00",
		"#00CC00",		
	};
}
