package com.jt.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jt.anno.Cache_Find;
import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	/**
	 * 根据商品分类id信息,查询商品分类名称
	 * 	url:/item/cat/queryItemName
	 * 	参数:{itemCatId:val}
	 *  返回值: 商品分类名称
	 **/
	@RequestMapping("/queryItemName")
	public String findItemCatNameById(Long itemCatId) {
		
		ItemCat itemCat = 
				itemCatService.findItemCatNameById(itemCatId);
		return itemCat.getName();
	}
	
	/**
	 * 查询商品分类信息,进行树形结构展现
	 * 
	 * 查询一级菜单
		SELECT * FROM tb_item_cat WHERE parent_id=0
		/*查询二级菜单
		SELECT * FROM tb_item_cat WHERE parent_id=558
		/*查询三级菜单
		SELECT * FROM tb_item_cat WHERE parent_id=567
		
		?id=节点的Id值
		
		@RequestParam(name="id",defaultValue = "0",required = true) Long parentId)
		设定参数:
			name/value: 接收参数名称
			defaultValue:默认值
			required: 是否为必填项.
	 */
	@RequestMapping("/list")
	@Cache_Find
	public List<EasyUITree> findItemCatAll(
			@RequestParam(name="id",
						  defaultValue = "0",
						  required = true) 
						  Long parentId){
		//查询数据库!!!
		return itemCatService.findItemCatAll(parentId);
		//return itemCatService.findItemCatCache(parentId);
	}
	
	
	//@RequestMapping("")	//添加拦截路径
	//@ResponseBody		//返回json数据
	//localhost:8091/{name}/{age}
	//@PathVariable		//restFul风格中获取url中的参数
	//localhost:8091?name="abc"
	//@RequestParam		//获取请求参数的注解
	//@Param  			//封装mybatis中的参数
	/*public String xxx(@Param ) {
		
	}*/
	
	
	
	
	
	
}
