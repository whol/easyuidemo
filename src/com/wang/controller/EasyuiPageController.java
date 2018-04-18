package com.wang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wang.controller.base.BaseController;
import com.wang.model.common.Page;
import com.wang.model.common.PageData;
import com.wang.service.EasyuiPageService;


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
		List<HashMap<String, Object>> list = easyuiPageService.querylistPage(page);
		return this.getEasyUIReturnDataJson(list, page.getTotalResult());
	}
}
