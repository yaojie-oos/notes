package com.jt.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	
	/**
	 * 根据用户请求,跳转对应的页面
	 * 	/page/item-add
	 * 	/page/item-list
	 * 	/page/item-param-list
	 * 
	 * 需求:通过一个方法拦截,实现通用页面跳转
	 * 思路:只要动态获取url中的参数,可以实现页面跳转
	 * 
	 * 语法规则: SpringMVC 动态获取url中的数据!!!
	 * 		1.参数必须使用{}包裹
	 * 		2.参数与参数之间使用/分割,参数位置固定
	 * 		3.利用@PathVariable注解接收,并且名称一致!!!
	 * 
	 * restFul风格:
	 * 		1.动态的获取用户参数!!!
	 * 		2.请求路径是不变的,根据请求方式的不同实现CRUD操作
	 * 需求:
	 * 		实现user的CRUD操作 		
	 */
	
	//@RequestMapping(value = "/user",method = RequestMethod.POST)
	/**
	@PostMapping("/user")
	public String insert() {
		System.out.println("用户新增");
		return "";
	}
	
	//@RequestMapping(value = "/user",method = RequestMethod.DELETE)
	@DeleteMapping("/user")
	public String delete() {
		System.out.println("用户删除");
		return "";
	}
	
	//@RequestMapping(value = "/user",method = RequestMethod.PUT)
	@PutMapping("/user")
	public String update() {
		System.out.println("用户更新");
		return "";
	}
	
	//@RequestMapping(value = "/user",method = RequestMethod.GET)
	@GetMapping("/user")
	public String select() {
		System.out.println("用户查询");
		return "";
	}
	
	@GetMapping("/user")
	public String select(User user) {
		System.out.println("用户查询");
		return "";
	}
	**/
	
	
	
	@RequestMapping("/page/{moduleName}")
	public String module(@PathVariable String moduleName) {
		
		return moduleName;
	}
	
	
	@Value("${server.port}")
	private String port;
	
	@RequestMapping("/getMsg")
	@ResponseBody
	public String getMsg() {
		
		return "我是"+port+"项目!!!";
	}
	
	
	
	
	
	
	
}
