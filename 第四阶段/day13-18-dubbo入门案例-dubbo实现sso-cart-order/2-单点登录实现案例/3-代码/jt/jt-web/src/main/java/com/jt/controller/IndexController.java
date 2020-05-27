package com.jt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.util.IPUtil;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		String ip = IPUtil.getIpAddr(request);
		return "index";
		
	}
}
