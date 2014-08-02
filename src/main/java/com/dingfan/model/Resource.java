package com.dingfan.model;

import java.util.Set;

/**
 * ResourceDetails的实现类
 *
 */
public class Resource implements java.io.Serializable{
	
	private static final long serialVersionUID = 4946058421074138801L;
	public static final String URL_TYPE = "URL";
	public static final String MENU_TYPE = "MENU";

	private Integer id;

	private String name;
	
	private String resString;

	private String resType;
	
	private String descn;

	private Set<Role> roleSet;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResString() {
		return resString;
	}

	public void setResString(String resString) {
		this.resString = resString;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}


}
