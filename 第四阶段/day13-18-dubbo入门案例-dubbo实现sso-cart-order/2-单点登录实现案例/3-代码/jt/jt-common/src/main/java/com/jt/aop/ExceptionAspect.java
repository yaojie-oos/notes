package com.jt.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jt.vo.SysResult;

import lombok.extern.slf4j.Slf4j;

//@ControllerAdvice	//定义Controller层切面定义
@RestControllerAdvice
@Slf4j
public class ExceptionAspect {
	
	//当出现某类异常 该方法执行
	@ExceptionHandler(RuntimeException.class)
	public SysResult result(Exception exception) {
		exception.printStackTrace(); //为程序员调错方便准备.

		log.error("异常信息:", exception);
		return SysResult.fail();
	}
}
