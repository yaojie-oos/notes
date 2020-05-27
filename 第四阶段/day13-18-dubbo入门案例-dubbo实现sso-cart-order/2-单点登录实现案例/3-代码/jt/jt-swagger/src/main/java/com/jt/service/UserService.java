package com.jt.service;

import java.util.List;

import com.jt.pojo.User;

public interface UserService {

	List<User> findAll();

	User findUserById(Integer id);

	void saveUser(User user);

	void deleteUser(Integer id);

	void updateUser(User user);
	
}
