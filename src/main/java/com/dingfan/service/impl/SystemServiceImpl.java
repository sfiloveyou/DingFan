package com.dingfan.service.impl;

import com.dingfan.dao.UserDao;
import com.dingfan.model.User;
import com.dingfan.service.SystemService;



public class SystemServiceImpl implements SystemService {
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * 	根据login查询user
	 *
	 * @param String login
	 * @return User
	 * author cloud.shi
	 * date 2010.05.25
	 */
	public User getUserByLogin(String login){
		return userDao.getUserByLogin(login);
	}
}
