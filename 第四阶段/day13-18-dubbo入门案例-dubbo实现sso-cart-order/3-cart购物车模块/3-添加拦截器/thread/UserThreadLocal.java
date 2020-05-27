package com.jt.thread;

import com.jt.pojo.User;

public class UserThreadLocal {
	
	private static ThreadLocal<User> userThread = new ThreadLocal<>();
	
	public static void set(User user){
		
		userThread.set(user);
	}
	
	public static User get(){
		
		return userThread.get();
	}
	
	//防止内存泄漏
	public static void remove(){
		
		userThread.remove();
	}
	
}
