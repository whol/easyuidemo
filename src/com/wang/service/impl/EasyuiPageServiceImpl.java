package com.wang.service.impl;

import com.wang.common.util.DateUtils;
import com.wang.dao.EasyuiPageDao;
import com.wang.model.common.Page;
import com.wang.model.common.PageData;
import com.wang.service.EasyuiPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("easyuiPageService")
public class EasyuiPageServiceImpl implements EasyuiPageService {

	@Autowired
	private EasyuiPageDao easyuiPageDao;
	public List<PageData> querylistPage(Page page) {
		List<PageData> list = (List<PageData>) easyuiPageDao.querylistPage(page);
		this.formatValueState(list);
		return list;
	}

	/**
	 * 格式化呈现到前台的数据
	 * @param list
	 */
	private void formatValueState(List<PageData> list) {

		if(list !=null && list.size() >0) {
			for(int i=0;i<list.size();i++) {
				PageData pd = list.get(i);
				//创建时间
				try{
					pd.put("createTime", DateUtils.format((Date) pd.get("createTime")));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
