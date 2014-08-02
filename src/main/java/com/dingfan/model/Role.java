package com.dingfan.model;

import java.util.Set;

public class Role implements java.io.Serializable{
	
	private static final long serialVersionUID = -1897362371074480788L;
	
	private Integer id;
	private String name;
	private Set<Resource> resourceSet;
	
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
	
	public Set<Resource> getResourceSet() {
		return resourceSet;
	}
	public void setResourceSet(Set<Resource> resourceSet) {
		this.resourceSet = resourceSet;
	}
	
}
