package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.EasyUITree;

import redis.clients.jedis.Jedis;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper;
	//@Autowired //引入缓存对象
	private Jedis jedis;

	@Override
	public ItemCat findItemCatNameById(Long itemCatId) {
		
		return itemCatMapper.selectById(itemCatId);
	}
	
	/**
	 * 返回值结果:List<EasyUITree>
	 * 数据来源:  tb_item_cat表~~~~~List<ItemCat>
	 * 转化过程:  List<ItemCat>~~~~~List<EasyUITree>
	 * 
	 */
	@Override
	public List<EasyUITree> findItemCatAll(Long parentId) {
		//0.定义返回值数据
		List<EasyUITree> treeList = new ArrayList<EasyUITree>(); 
		
		//1.获取数据库数据!!
		List<ItemCat> catList = findItemCatList(parentId);
		
		//2.实现数据转化  一级/二级 closed 三级 open
		for (ItemCat itemCat : catList) {
			EasyUITree easyUITree = new EasyUITree();
			String state = 
			itemCat.getIsParent()?"closed":"open";
			easyUITree.setId(itemCat.getId())
					  .setText(itemCat.getName())
					  .setState(state);
			treeList.add(easyUITree);
		}
		return treeList;
	}

	private List<ItemCat> findItemCatList(Long parentId) {
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<ItemCat>();
		queryWrapper.eq("parent_id", parentId);
		return itemCatMapper.selectList(queryWrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EasyUITree> findItemCatCache(Long parentId) {
		List<EasyUITree> treeList = new ArrayList<>();
		//1.根据key查询缓存
		String key = "ITEM_CAT::"+parentId;
		String result = jedis.get(key);
		//2.检查是否有数据
		if(StringUtils.isEmpty(result)) {
			//缓存中没有数据,查询数据库.
			treeList = findItemCatAll(parentId);
			//将数据转化为JSON串
			String dataJSON = ObjectMapperUtil.toJSON(treeList);
			//将数据保存到redis中
			jedis.set(key, dataJSON);
			System.out.println("第一次查询数据库");
		}else {
			//3.表示缓存中有数据的
			treeList = 
					ObjectMapperUtil.toObject(result,treeList.getClass());
			System.out.println("查询缓存数据!!!!!");
		}
		
		return treeList;
	}
	
	
	
	
	
	
	
	
}
