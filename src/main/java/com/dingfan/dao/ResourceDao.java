package com.dingfan.dao;

import java.util.List;

import com.dingfan.model.Resource;

public interface ResourceDao {

	Resource getResourceById(Integer id);

	List<Resource> getResourceByUrl();

}
