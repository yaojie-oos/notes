package com.jt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemDesc;

public class TestObjectToJSON {
	
	
	
	/**
	 * 问题:对象转化为json时,依赖对象的哪些方法?
	 * 依据:每个对象中必须要get/set
	 * @throws IOException 
	 */
	private ObjectMapper mapper = new ObjectMapper();
	@Test
	public void toJSON() throws IOException {
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(100L);
		itemDesc.setItemDesc("定义详情信息");
		String json = 
		mapper.writeValueAsString(itemDesc);
		System.out.println(json);
		//2.json转化为对象
		//src:代表json串  valueType代表类型
		ItemDesc desc = 
				mapper.readValue(json,ItemDesc.class);
		System.out.println(desc);
	}
	
	/**
	 * List集合转化为JSON
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void toList() throws IOException {
		List<ItemDesc> list = new ArrayList<ItemDesc>();
		ItemDesc itemDesc1 = new ItemDesc();
		itemDesc1.setItemId(100L).setItemDesc("定义详情信息");
		ItemDesc itemDesc2 = new ItemDesc();
		itemDesc2.setItemId(100L).setItemDesc("定义详情信息");
		list.add(itemDesc1);
		list.add(itemDesc2);
		String json = mapper.writeValueAsString(list);
		System.out.println(json);
		List<ItemDesc> descList = 
		mapper.readValue(json, list.getClass());
		System.out.println(descList);
	}
	
	
	
}
