package com.jt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.util.HttpClientService;
import com.jt.util.ObjectMapperUtil;

/**
 * 根据HttpClient方式获取数据
 * @author Administrator
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private HttpClientService httpClient;
	
	@Override  //SDK.jar 调用API
	public Item findItemById(Long id) {
		String url = "http://manage.jt.com/web/item/findItemById/"+id;
		String itemJSON = httpClient.doGet(url);
		return ObjectMapperUtil.toObject(itemJSON, Item.class);
	}

	@Override
	public ItemDesc findItemDescById(Long id) {
		String url = "http://manage.jt.com/web/item/findItemDescById/"+id;
		String itemDescJSON = httpClient.doGet(url);
		return ObjectMapperUtil.toObject(itemDescJSON, ItemDesc.class);
	}
	
}
