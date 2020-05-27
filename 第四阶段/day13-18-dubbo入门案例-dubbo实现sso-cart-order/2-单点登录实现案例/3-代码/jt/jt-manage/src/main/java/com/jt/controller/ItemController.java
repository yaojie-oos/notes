package com.jt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;

@RestController //为ajax返回数据,不能跳转页面 
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 展现商品列表数据
	 * url: http://localhost:8091/item/query?page=1&rows=40
	 */
	@RequestMapping("/query")
	public EasyUITable findItemByPage(Integer page,Integer rows) {
		
		return itemService.findItemByPage(page,rows);
	}
	
	/**
	 * 实现商品新增
	 * try {
			itemService.saveItem(item); 
			return SysResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	 */
	
	/**
	 * 参数:  页面表单数据 $("#itemAddForm").serialize() key=value
	 * 获取参数名称: 通过反射实例化对象,
	 * 	    getXXX()~~~~~get去掉~~~XXX首字母小写~~~xxx
	 * 取值:  request.getParameter("参数名称xxx");
	 * 赋值:  对象.setXXX(value);
	 * @param item
	 * @return
	 */
	@RequestMapping("/save")  
	public SysResult saveItem(Item item,ItemDesc itemDesc) {
		
		itemService.saveItem(item,itemDesc); 
		return SysResult.success();
	}
	
	@RequestMapping("/update")
	public SysResult updateItem(Item item,ItemDesc itemDesc) {
		
		itemService.updateItem(item,itemDesc);
		return SysResult.success();
	}
	
	/**
	 * 实现商品删除
	 * var params = {"ids":ids};
	 */
	@RequestMapping("/delete")
	public SysResult deleteItems(Long[] ids) {
		
		itemService.deleteItmes(ids);
		return SysResult.success();
	}
	
	/**
	 * 实现商品下架 
	 * localhost:8091/item/instock
	 */
	@RequestMapping("/instock")
	public SysResult instock(Long[] ids) {
		int status = 2;
		itemService.updateItemStatus(status,ids);
		return SysResult.success();
	}
	
	@RequestMapping("/reshelf")
	public SysResult reshelf(Long[] ids) {
		int status = 1;
		itemService.updateItemStatus(status,ids);
		return SysResult.success();
	}
	
	
	/*
	 * @RequestMapping("/{moduleName}") public SysResult instock(
	 * 
	 * @PathVariable String moduleName, Long[] ids) {
	 * if("instock".equals(moduleName)) { //下架操作 int status = 2;
	 * itemService.updateStatus(status,ids); }else
	 * if(("reshelf").equals(moduleName)) { //上架 int status = 1;
	 * itemService.updateStatus(status,ids); }else { return SysResult.fail(); }
	 * 
	 * return SysResult.success(); }
	 */
	
	
	/**
	 * 目的:展现商品详情信息
	 * url: /item/query/item/desc/'+data.id
	 * 		http://localhost:8091/item/query/item/desc/1474391972
	 * 
	 *   参数:  感觉不用参数     Id信息
	 *   业务操作: 根据商品Id信息查询商品详情信息
	 *   返回值数据: 检查js中的回调
	 */
	
	@RequestMapping("/query/item/desc/{id}")
	public SysResult findItemDescById(@PathVariable Long id) {
		
		ItemDesc itemDesc = itemService.findItemDescById(id);
		return SysResult.success(itemDesc);
		//Sysresult.data=itemDesc数据
	}
	
	
	
	
	
	
	
	
}
