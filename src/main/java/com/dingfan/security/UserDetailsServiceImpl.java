package com.dingfan.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.dingfan.dao.UserDao;
import com.dingfan.model.Role;
import com.dingfan.model.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 获取用户Details信息的回调函数.
	 */
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException {

		User user = userDao.getUserByLogin(login);
		if (user == null)
			throw new UsernameNotFoundException("用户" + login + " 不存在");

		GrantedAuthority[] grantedAuths = obtainGrantedAuthorities(user);

		boolean enable = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		org.springframework.security.userdetails.User userdetail = new org.springframework.security.userdetails.User(
				user.getLogin(), user.getPassword(), enable, accountNonExpired, credentialsNonExpired,
				accountNonLocked, grantedAuths);

		return userdetail;
	}

	/**
	 * 获得用户所有角色的权限集合.
	 */
	private GrantedAuthority[] obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Role role : user.getRoleSet()) {
			authSet.add(new GrantedAuthorityImpl(role.getName()));
		}
		return authSet.toArray(new GrantedAuthority[authSet.size()]);
	}
}
