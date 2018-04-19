package com.wang.service;

import com.wang.model.common.Page;
import com.wang.model.common.PageData;
import java.util.List;

public interface EasyuiPageService {
	
	List<PageData> querylistPage(Page page);
}
