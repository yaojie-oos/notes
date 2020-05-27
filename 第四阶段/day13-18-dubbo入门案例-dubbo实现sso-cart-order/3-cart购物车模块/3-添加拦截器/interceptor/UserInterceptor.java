package com.jt.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.User;
import com.jt.thread.UserThreadLocal;
import com.jt.util.ObjectMapperUtil;

import redis.clients.jedis.JedisCluster;

//定义用户拦截器
@Component
public class UserInterceptor implements HandlerInterceptor{
	
	@Autowired
	private JedisCluster jedisCluster;
	
	/**
	 * 1.获取用户Cookie获取token数据
	 * 2.判断token中是否有数据
	 * 		false 表示没有登陆,则重定向到用户登陆页面
	 * 		
	 * 		true :表示用户之前登陆过		
	 * 			从redis中根据token获取userJSON,
	 * 			再次判断数据是否有数据
	 * 			
	 * 			false: 没有数据,则重定向到用户登陆页面
	 * 			true : 表示有数据,则程序予以放行.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1.获取Cookiez中的token
		String token = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if("JT_TICKET".equals(cookie.getName())){
				token = cookie.getValue();
				break;
			}
		}
		
		//2.判断token是否有数据
		if(token != null){
			//2.1判断redis集群中是否有数据
			String userJSON = jedisCluster.get(token);
			if(userJSON != null){
				User user = ObjectMapperUtil.toObject(userJSON,User.class);
				//将user数据保存到ThreadLocal中
				UserThreadLocal.set(user);
				//证明用户已经登陆 予以放行
				return true;
			}
		}
		
		//配置重定向
		response.sendRedirect("/user/login.html");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		//关闭threadLocal
		UserThreadLocal.remove();
	}
}
