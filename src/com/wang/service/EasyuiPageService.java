package com.wang.service;

import java.util.HashMap;
import java.util.List;

import com.wang.model.common.Page;

public interface EasyuiPageService {
	
	List<HashMap<String, Object>> querylistPage(Page page);
}
