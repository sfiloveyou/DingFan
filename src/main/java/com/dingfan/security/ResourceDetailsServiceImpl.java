package com.dingfan.security;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.dingfan.dao.ResourceDao;
import com.dingfan.model.Resource;
import com.dingfan.model.Role;

public class ResourceDetailsServiceImpl implements ResourceDetailsService {
	private ResourceDao resourceDao;

	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	/**
	 * @see ResourceDetailsService#getRequestMap()
	 */
	public LinkedHashMap<String, String> getRequestMap() throws Exception {
		List<Resource> resourceList = resourceDao.getResourceByUrl();

		LinkedHashMap<String, String> requestMap = new LinkedHashMap<String, String>(resourceList.size());
		for (Resource resource : resourceList) {
			Set<Role> roleSet=resource.getRoleSet();
			if(CollectionUtils.isNotEmpty(roleSet)){
				StringBuffer str=new StringBuffer();
				for (Role role : roleSet) {
					str.append(role.getName());
					str.append(",");
				}
				requestMap.put(resource.getResString(), str.substring(0, str.length()-1).toString());
			}
				
				
		}
		return requestMap;
	}
}
