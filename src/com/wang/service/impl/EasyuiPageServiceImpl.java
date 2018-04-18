package com.wang.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wang.dao.EasyuiPageDao;
import com.wang.model.common.Page;
import com.wang.service.EasyuiPageService;

@Service("easyuiPageService")
public class EasyuiPageServiceImpl implements EasyuiPageService {

	@Autowired
	private EasyuiPageDao easyuiPageDao;
	public List<HashMap<String, Object>> querylistPage(Page page) {
		return easyuiPageDao.querylistPage(page);
	}

}
