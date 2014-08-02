package com.dingfan.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.dingfan.dao.BaseDao;
import com.dingfan.dao.UserDao;
import com.dingfan.model.User;


public class UserDaoImpl extends BaseDao implements UserDao{
	/**
	 * 	根据id查询user
	 *
	 * @param Integer id
	 * @return User
	 * author cloud.shi
	 * date 2010.05.25
	 */
	public User getUserById(Integer id) {
		if(id!=null && id>0)
			return (User) getHibernateTemplate().get(User.class, id);
		else
			return null;
	}
	/**
	 * 	根据login查询user
	 *
	 * @param String login
	 * @return User
	 * author cloud.shi
	 * date 2010.05.25
	 */
	@SuppressWarnings("unchecked")
	public User getUserByLogin(String login) {
		StringBuffer sql=new StringBuffer();
		List<Object> param=new ArrayList<Object>();
		
		sql.append("  from User u where 1=1 and enabled = 1");
		
		if(StringUtils.isNotBlank(login)){
			sql.append(" and u.login = ?  ");
			param.add(login);
		}
		List<User> userList=createQuery(sql.toString(), param.toArray()).setCacheable(true).list();
		if(CollectionUtils.isNotEmpty(userList))
			return userList.get(0);
		else
			return null;
	}
	/**
	 * 	查询所有user
	 *
	 * @param 
	 * @return User
	 * author cloud.shi
	 * date 2010.05.25
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUser(){
		return createQuery(" from User u ").setCacheable(true).list();
	}	
}
