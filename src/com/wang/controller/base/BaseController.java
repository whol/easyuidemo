package com.wang.controller.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.wang.model.common.Page;

public class BaseController {
	/**
	 * 得到request对象
	 */
	protected HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 获取EasyUI异步请求时的当前页和记录数参�?
	 * 
	 * @return
	 */
	protected Page getEasyUIAjaxPage() {
		return getEasyUIAjaxPage(new Page());
	}

	protected Page getEasyUIAjaxPage(Page page) {
		int pagenum = 0;
		int rows = 10;
		if (null != this.getRequest().getParameter("page")) {
			pagenum = Integer.parseInt(this.getRequest().getParameter("page"));
		}
		if (null != this.getRequest().getParameter("rows")) {
			rows = Integer.parseInt(this.getRequest().getParameter("rows"));
		}
		page.setPage(pagenum);
		page.setRows(rows);
		return page;
	}

	/**
	 * 返回EasyUI格式的json数据.
	 * 
	 * @param list
	 * @param totalSize
	 * @return
	 */
	protected String getEasyUIReturnDataJson(List<?> list, int totalSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", totalSize);
		map.put("rows", list);
		return new Gson().toJson(map);
	}

	/**
	 * ajax请求返回的map
	 * 
	 * @param result
	 *            结果,true表示成功,false表示失败
	 * @param errinfo
	 *            失败的原�?
	 * @return
	 */
	protected Map<String, Object> ajaxReturnMap(boolean result, String errinfo) {
		Map<String, Object> returnmap = new HashMap<String, Object>();
		returnmap.put("result", result);
		returnmap.put("errinfo", errinfo);
		return returnmap;
	}

}
