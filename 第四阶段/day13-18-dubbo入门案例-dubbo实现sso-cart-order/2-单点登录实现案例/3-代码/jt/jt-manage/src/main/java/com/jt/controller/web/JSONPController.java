package com.jt.controller.web;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.ItemDesc;
import com.jt.util.ObjectMapperUtil;

@RestController
public class JSONPController {
	
	
	/**
	 * 利用API简化调用
	 */
	@RequestMapping("/web/testJSONP")
	public JSONPObject  testJSONPObject(String callback) {
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(10010L)
		        .setItemDesc("JSONP的跨域访问!!!!")
		        .setCreated(new Date())
		        .setUpdated(new Date());
		return new JSONPObject(callback, itemDesc);
	}
	
	
	/**
	 * http://manage.jt.com/web/testJSONP?callback=jQuery111105212998482408604_1568169989123&_=1568169989124
	 * @return
	 */
	public String testJSONP(String callback) {
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(10010L);
		itemDesc.setItemDesc("JSONP的跨域访问!!!!");
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		String json = ObjectMapperUtil.toJSON(itemDesc);
		return callback+"("+json+")";
	}
}
