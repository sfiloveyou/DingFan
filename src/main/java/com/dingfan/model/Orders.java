package com.dingfan.model;

import java.util.Date;

public class Orders implements java.io.Serializable {
	private static final long serialVersionUID = 469183540068082251L;
	
	public interface statusId{
		int normal = 1;
		int delete = 0;
	};
	
	private Integer id;
	private String personName;
	private String amount;
	private Integer paid;
	private Date entertime;
	private String lastIp;
	private String comments;
	private Integer status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Integer getPaid() {
		return paid;
	}
	public void setPaid(Integer paid) {
		this.paid = paid;
	}
	public Date getEntertime() {
		return entertime;
	}
	public void setEntertime(Date entertime) {
		this.entertime = entertime;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	

	
}