package com.dingfan.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dingfan.dao.OrdersDao;
import com.dingfan.model.Orders;
import com.dingfan.service.DingFanService;
import com.dingfan.utils.DateUtil;

public class DingFanServiceImpl implements DingFanService{
	protected final Log log = LogFactory.getLog(getClass());
	private OrdersDao ordersDao;
	
	public OrdersDao getOrdersDao() {
		return ordersDao;
	}
	public void setOrdersDao(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}
	public Orders getOrdersById(Integer id){
		return null;
	}
	public List getOrdersByEntertime(String entertime){
		try {
			Date beginDate =  DateUtil.convertStringToDate(entertime);  
			Date endDate =   DateUtil.convertStringToDate(DateUtil.addDay(entertime, 1));
			return ordersDao.getOrdersByEntertime(beginDate,endDate);
		} catch (ParseException e) {
			log.error(e);
			return null;
		}
	}
	public List getOrdersByToday(){
		return getOrdersByEntertime(DateUtil.getDate());
	}
	public boolean saveOrders(String personName,String amount, String comments, String ip){
		Orders o = new Orders();
		o.setPersonName(personName);
		o.setAmount(amount);
		o.setEntertime(new Date());
		o.setPaid(0);
		o.setComments(comments);
		o.setLastIp(ip);
		o.setStatus(Orders.statusId.normal);
		ordersDao.save(o);
		return true;
	}
	public List getNamesFromOrders(String personName){
		try {
			
			Date beginDate =  DateUtil.getOneMonthAgo(); 
			Date endDate =   DateUtil.convertStringToDate(DateUtil.addDay(DateUtil.getDate(),1));  
			List Names = ordersDao.getNamesFromOrders(personName,beginDate,endDate);
			List personNames = new ArrayList();
			for (Object name : Names) {
				Orders o = new Orders();
				o.setPersonName((String)name);
				personNames.add(o);
			}
			return personNames;
		} catch (Exception e) {
			log.error(e);
			return null;
		}

	}
	
	public void del(String ids, String ip){
		String[] idArray=ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			Orders o = ordersDao.get(Integer.valueOf(idArray[i]));
			o.setStatus(Orders.statusId.delete);
			o.setLastIp(ip);
			ordersDao.save(o);
		}
	}
	
	public void pay(String ids, String ip){
		String[] idArray=ids.split(",");
		for (int i = 0; i < idArray.length; i++) {
			Orders o = (Orders)ordersDao.get(Integer.valueOf(idArray[i]));
			o.setPaid(1);
			o.setLastIp(ip);
			ordersDao.save(o);
		}		
	}
}
