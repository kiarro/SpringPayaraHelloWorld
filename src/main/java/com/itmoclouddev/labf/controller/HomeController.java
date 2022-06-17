package com.itmoclouddev.labf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public RedirectView toHello() {
		return new RedirectView("index");
	}

	@RequestMapping(value="/index")
	public ModelAndView hello() {
		ModelAndView ret = new ModelAndView("index1");
		return ret;
	}

	@RequestMapping(value="/home")
	public ModelAndView home() {
		ModelAndView ret = new ModelAndView("home");
		return ret;
	}
}
