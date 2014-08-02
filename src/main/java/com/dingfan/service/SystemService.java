package com.dingfan.service;

import com.dingfan.model.User;

public interface SystemService {
	/**
	 * 	根据login查询user
	 *
	 * @param String login
	 * @return User
	 * author cloud.shi
	 * date 2010.05.25
	 */
	public User getUserByLogin(String login);
}
