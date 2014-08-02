package com.dingfan.service;

import java.util.List;

import com.dingfan.model.Orders;

public interface DingFanService {
	public Orders getOrdersById(Integer id);
	public List getOrdersByEntertime(String entertime);
	public List getOrdersByToday();
	public boolean saveOrders(String personName,String amount, String comments, String ip);
	public List getNamesFromOrders(String personName);
	public void del(String ids, String ip);
	public void pay(String ids, String ip);
}
