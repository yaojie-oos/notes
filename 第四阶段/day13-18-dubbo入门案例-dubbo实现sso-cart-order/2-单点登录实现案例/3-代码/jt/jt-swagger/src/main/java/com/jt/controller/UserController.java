package com.jt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/findAll")
	@ApiOperation(value="查询全部用户信息",notes="查询数据库全部的用户信息")
	@ApiResponses({
		@ApiResponse(code=401,message="请求需要认证"),
		@ApiResponse(code=403,message="服务器拒绝请求"),
		@ApiResponse(code=404,message="服务器获取资源失败"),
		})
	public List<User> findAll(){
		
		return userService.findAll();
	}
	
	@GetMapping("/user/{id}")
	@ApiOperation(value="根据ID查询用户信息")
	@ApiResponses({
		@ApiResponse(code=401,message="请求需要认证"),
		@ApiResponse(code=403,message="服务器拒绝请求"),
		@ApiResponse(code=404,message="服务器获取资源失败"),
		})
	public User findUserById(@PathVariable Integer id) {
		
		return userService.findUserById(id);
	}
	
	@PostMapping("/user/{name}/{age}/{sex}")
	@ApiOperation(value="新增用户")
	public String saveUser(User user) {
		
		userService.saveUser(user);
		return "用户新增成功!!!";
	}
	
	@DeleteMapping("/user/{id}")
	@ApiOperation(value="删除用户")
	public String deleteUser(@PathVariable Integer id) {
		
		userService.deleteUser(id);
		return "删除用户成功!!!";
	}
	
	@PutMapping("/user/{id}/{name}/{age}/{sex}")
	@ApiOperation(value="更新用户")
	public String updateUser(User user) {
		
		userService.updateUser(user);
		return "更新用户成功!!!!";
	}
	
	
	
}
