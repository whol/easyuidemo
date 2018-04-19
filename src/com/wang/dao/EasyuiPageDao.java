package com.wang.dao;

import com.wang.model.common.Page;
import com.wang.model.common.PageData;

import java.util.List;

public interface EasyuiPageDao {
	List<PageData> querylistPage(Page page);
}
