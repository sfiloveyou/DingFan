package com.dingfan.test;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.Before;
import org.junit.Test;

import com.dingfan.webservice.client.DingFanWebService;
import com.dingfan.webservice.client.OrderRequest;


public class OrderTest {
	@Before
	public void before() {


	}
	@Test
	public void addOrderTest() {
		try {
			//创建访问wsdl服务地址的url
			URL url = new URL("http://localhost:8080/DingFan/webservice/DingFanService?wsdl");
			//通过Qname指明服务的具体信息
			QName sname = new QName("http://wwww.dingfan.org/webservice/DingFanWebService", "DingFanService");
			//创建服务
			Service service = Service.create(url,sname);
			//实现接口
			DingFanWebService dingfanWebService = service.getPort(DingFanWebService.class);
			
			OrderRequest orderRequest = new OrderRequest();
			orderRequest.setPersonName("Cloud Shi");
			orderRequest.setAmount("1");
			orderRequest.setComments("红烧肉");
			
			for (int i = 0; i < 10; i++) {
				dingfanWebService.addOrder(orderRequest);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
