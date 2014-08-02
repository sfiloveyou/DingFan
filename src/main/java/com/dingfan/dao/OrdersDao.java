package com.dingfan.dao;

import java.util.Date;
import java.util.List;

import com.dingfan.dao.support.EntityDao;
import com.dingfan.model.Orders;

public interface OrdersDao extends EntityDao<Orders>{
	public List getOrdersByEntertime(Date beginDate,Date endDate);
	public List getNamesFromOrders(String personName,Date beginDate,Date endDate);
	
}
