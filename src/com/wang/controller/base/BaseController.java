package com.wang.controller.base;

import com.google.gson.Gson;
import com.wang.common.util.ExcelUtil;
import com.wang.model.common.Page;
import com.wang.model.common.PageData;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


	/**
	 * 得到分页列表的信息
	 */
	public Page getPage() {
		return new Page();
	}

	public PageData getPageData() {
		return new PageData(this.getRequest());
	}

	public void toListExport(HttpServletRequest request,
							 HttpServletResponse response, Page page, List<PageData> list,
							 String fileName, String[] titles, String[] fields) throws Exception {
		this.toListExport(request, response, page, list, fileName, titles, fields, null);
	}
	public void toListExport(HttpServletRequest request,
							 HttpServletResponse response, Page page, List<PageData> list,
							 String fileName, String[] titles, String[] fields,HashMap<String, String> dictMap) throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		SXSSFWorkbook wb = ExcelUtil.generateWorkbook2007(list, fileName, titles,
				fields,dictMap);
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String((fileName + ".xlsx").getBytes("gb2312"),
				"iso8859-1"));// 指定下载的文件名
		response.setContentType("application/vnd.ms-excel");
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(response.getOutputStream());
			wb.write(out);
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			if (out!=null) {
				try{out.flush();}catch (Exception e) {}
				try{out.close();}catch (Exception e) {}
			}
		}
	}

}
