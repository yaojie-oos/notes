package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;

public interface ItemService {

	EasyUITable findItemByPage(Integer page, Integer rows);

	void saveItem(Item item, ItemDesc itemDesc);

	void updateItem(Item item, ItemDesc itemDesc);

	void deleteItmes(Long[] ids);

	void updateItemStatus(Integer status, Long[] ids);

	ItemDesc findItemDescById(Long id);

	Item findItemById(Long id);
	
}
