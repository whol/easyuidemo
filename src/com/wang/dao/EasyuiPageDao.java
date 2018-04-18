package com.wang.dao;

import java.util.HashMap;
import java.util.List;

import com.wang.model.common.Page;

public interface EasyuiPageDao {
	List<HashMap<String, Object>> querylistPage(Page page);
}
