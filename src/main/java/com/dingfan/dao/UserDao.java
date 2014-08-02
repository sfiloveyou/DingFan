package com.dingfan.dao;

import java.util.List;

import com.dingfan.model.User;

public interface UserDao{
	/**
	 * 	根据id查询user
	 *
	 * @param Integer id
	 * @return User
	 * author cloud.shi
	 * date 2010.05.25
	 */
	public User getUserById(Integer id);
	/**
	 * 	根据login查询user
	 *
	 * @param String login
	 * @return User
	 * author cloud.shi
	 * date 2010.05.25
	 */
	public User getUserByLogin(String login);
	/**
	 * 	查询所有user
	 *
	 * @param 
	 * @return User
	 * author cloud.shi
	 * date 2010.05.25
	 */
	public List<User> getAllUser();	
	
}
