package com.dingfan.dao.impl;


import java.util.List;

import com.dingfan.dao.BaseDao;
import com.dingfan.dao.ResourceDao;
import com.dingfan.model.Resource;


public class ResourceDaoImpl extends BaseDao implements ResourceDao{
	public Resource getResourceById(Integer id) {
		if(id!=null && id>0)
			return (Resource) getHibernateTemplate().get(Resource.class, id);
		else
			return null;
	}
	@SuppressWarnings("unchecked")
	public List<Resource> getResourceByUrl() {
		return createQuery(" from Resource r where 1=1 and r.resType = '"+Resource.URL_TYPE+"'"+" order by id desc ").setCacheable(true).list();
	}
}
