package com.wang.controller;

import com.wang.controller.base.BaseController;
import com.wang.model.common.Page;
import com.wang.model.common.PageData;
import com.wang.service.EasyuiPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/easyuipage") 
public class EasyuiPageController extends BaseController  {

	@Autowired
	private EasyuiPageService easyuiPageService;
	/**
	 * 全部工单
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toAllOfDemo")  
	public ModelAndView toAllOfOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv =new ModelAndView();
		mv.setViewName("/easyuipage/all_of_demo");
		return mv; 
	}

	/**
	 * 获取easyUI异步加载的json数据 查看异常工单列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/listajax")
	@ResponseBody
	public String listajax(Map<String, Object> params) {
		Page page = getEasyUIAjaxPage();
		PageData pd = new PageData(this.getRequest());
		page.setPd(pd);
		List<PageData> list = easyuiPageService.querylistPage(page);
		return this.getEasyUIReturnDataJson(list, page.getTotalResult());
	}


	@RequestMapping(value = "/exportDemo", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void exportsourceorder(HttpServletRequest request,
								  HttpServletResponse response) throws Exception {
		Page page = getEasyUIAjaxPage();
		page.setExport(true);
		PageData pd = this.getPageData();
		page.setPd(pd);
		String xlstitle = "Demo报表";

		List<PageData> list = easyuiPageService.querylistPage(page);
		String[] titles = { "ID", "姓名", "年龄", "创建时间"};
		String[] fields = { "id", "name", "age", "createTime"};
		this.toListExport(request, response, page, list, xlstitle, titles, fields);

	}
}
