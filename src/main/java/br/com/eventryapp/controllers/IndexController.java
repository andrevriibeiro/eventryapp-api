package br.com.eventryapp.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eventryapp")
public class IndexController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

}
