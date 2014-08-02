package com.dingfan.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import com.dingfan.dao.OrdersDao;
import com.dingfan.dao.support.HibernateEntityDao;
import com.dingfan.model.Orders;

public class OrdersDaoImpl extends HibernateEntityDao<Orders> implements OrdersDao{

	public List getOrdersByEntertime(Date beginDate,Date endDate){
		String sql = " from Orders o where o.entertime >= ? and o.entertime <=? and o.status != ?";
		Query query =createQuery(sql,beginDate,endDate,Orders.statusId.delete);
		
		return query.list();
	}
	
	public List getNamesFromOrders(String personName,Date beginDate,Date endDate) {
		String sql = "select distinct o.personName " +
				"from Orders o where o.personName like ?" +
				"and o.entertime >= ? and o.entertime <=?";
		
		Query query =createQuery(sql,personName+"%",beginDate,endDate);
		return query.list();
	}	
}
