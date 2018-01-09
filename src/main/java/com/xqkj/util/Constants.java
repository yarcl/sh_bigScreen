package com.xqkj.util;
public class Constants {
	public final static int SCANCACHING=20;
	public final static boolean CACHEBLOCKS = false;

	public final static String SPLIT_CHARACTERS_1 = "\001";
	public final static String USER_ACTION_CHARCTER = "\\|";

	public static final String FAMILY = "f";

	public static class UserTrackAction{
		public static final String TABLE_NAME = "user_action_track";

		public class QUALIFIER {
			public final static String EVENT_TIME = "event_time";
		}

	}
	public static final int SUCCESS_EVENT = 1; //成功的返回值
	public static final int UPDATA_EVENT = 2; //修改事件成功的返回值
	public static final int FIAL_EVENT = -1; //失败的返回值
	public static final int VIL_EVENT = -3; //验证失败返回值
	public static final int ERRER_EVENT = -2; //参数异常
	public static final int VIL_EVENTACTIVE = -4; //验证采集失败返回值

}
