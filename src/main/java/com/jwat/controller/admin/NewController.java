package com.jwat.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jwat.dto.NewDTO;
import com.jwat.service.ICategoryService;
import com.jwat.service.INewService;
import com.jwat.util.MessageUtil;

@Controller(value = "newControllerOfAdmin")
public class NewController {
	
	@Autowired
	private INewService newService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page,  HttpServletRequest request) {
		NewDTO model = new NewDTO();
		if(request.getParameter("page")!=null)
		{
			model.setPage(page);
			
			
		}
		else
		{
			model.setPage(1);
		}
		model.setLimit(21);
		ModelAndView mav = new ModelAndView("admin/new/list");
		Pageable pageable = new PageRequest(page - 1, 21);
		model.setListResult(newService.findAll(pageable));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editNew(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/new/edit");
		NewDTO model = new NewDTO();
		if (id != null) {
			model = newService.findById(id);
		}
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("categories", categoryService.findAll());
		mav.addObject("model", model);
		return mav;
	}
	@RequestMapping("/quan-tri/thong-ke")
	public ModelAndView report(HttpServletRequest request)
	{	NewDTO model = new NewDTO();
		model.setPage(1);
		model.setLimit(10);
		
		ModelAndView mav = new ModelAndView("admin/new/pdf");
		Pageable pageable = new PageRequest(1 - 1,10);
		model.setListResult(newService.report(pageable));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
}
