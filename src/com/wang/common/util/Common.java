package com.wang.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Common {
	public static String STATUS_YUYUE_SUCCESS = "3";// 预约
	public static String STATUS_YUYUE_FAILURE = "10";// 预约失败
	public static String STATUS_VISIT = "4";// 上门
	public static String STATUS_INSTALL_SUCCESS = "5";// 安装成功
	public static String STATUS_INSTALL_FAILURE = "11";// 安装失败
	public static String RESULT_FAILURE = "1";// 返回结果-失败
	public static String RESULT_SUCCESS = "0";// 返回结果-成功
	public static Long WORKCARD_STATUS_UPDATE_TYPE=1L;
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)|(btmall)).*"; // 不对匹配该值的访问路径拦截（正则）
	public static final String LOGIN = "/login/toLogin.do"; // 登录地址
	public static final String NOT_AUTH = "/login/noauth.do"; 
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_LOGINTYPE = "logintype";//登录类型pc表示PC端登录，mobile表示手机端登录
	public static final String LOGINTYPE_PC="pc";
	public static final String LOGINTYPE_MOBILE="mobile";
	public static final String SESSION_USER_AUTH = "sessionUserAuth";
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String RING_TYPE_MONTH="yue";
	public static final String RING_TYPE_WEEK="zhou";
	public static final String RING_TYPE_DAY="ri";
	public static final int CONFIG_TYPE_SYNCH =1;//同步配置信息.
	public static final int EXPORT_START_PAGE =1;
	public static final int EXPORT_PAGE_COUNT =1000;
	public static final int SYNCH_STATE_FINISHED=0;//同步完成.
	public static final int SYNCH_STATE_ING=1;//同步中.
	public static final String LOGIN_USER_BUTTONHANDLE = "operatorButtonHandle";//登录用户按钮权限

	public static final int OPERATE_LOGIN = 801;
	public static final String OPERATE_LOGIN_INFO = "用户登录";
	
	public static final int OPERATE_EXPORT = 802;
	public static final String OPERATE_EXPORT_INFO = "导出文件";
	
}
