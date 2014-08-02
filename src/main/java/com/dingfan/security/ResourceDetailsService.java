package com.dingfan.security;


import java.util.LinkedHashMap;

/**
 * RequestMap生成接口,由用户自行实现从数据库或其它地方查询URL-授权关系定义.
 * 
 * @author calvin
 */
public interface ResourceDetailsService {

	/*
	 *返回带顺序的URL-授权关系Map, key为受保护的URL, value为能访问该URL的以','分隔的授权名称列表.
	 */
	public LinkedHashMap<String, String> getRequestMap() throws Exception;
}
