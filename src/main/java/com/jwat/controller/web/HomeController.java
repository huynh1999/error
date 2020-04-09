package com.jwat.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javafx.geometry.Pos;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller(value = "homeControllerOfWeb")
public class HomeController {


	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	//<a href="<c:url value='/trang-chu/danh-sach?page=1&limit=2'/>">
	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("web/test");
	}

	@RequestMapping(value = "savefile", method = RequestMethod.POST)
	public String savefile(@RequestParam CommonsMultipartFile file, HttpSession session) throws FileNotFoundException {
		String path = session.getServletContext().getRealPath("/template/web/img");
		String filename = file.getOriginalFilename();

		System.out.println(path + " " + filename);
		try {
			byte barr[] = file.getBytes();

			BufferedOutputStream bout = new BufferedOutputStream(
					new FileOutputStream(path + "/" + filename));
			bout.write(barr);
			bout.flush();
			bout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "web/home";
	}
}
