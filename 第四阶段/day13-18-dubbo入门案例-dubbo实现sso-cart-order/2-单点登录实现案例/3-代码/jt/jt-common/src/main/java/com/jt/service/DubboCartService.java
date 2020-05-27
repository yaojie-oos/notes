package com.jt.service;

import java.util.List;

import com.jt.pojo.Cart;

/**
 * 定义dubbo 服务接口
 * @author Administrator
 */
public interface DubboCartService {

	List<Cart> findCartListByUserId(Long userId);

	void updateCartNum(Cart cart);

	void deleteCart(Cart cart);

}
