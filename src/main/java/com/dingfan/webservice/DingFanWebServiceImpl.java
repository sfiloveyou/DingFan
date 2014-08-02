package com.dingfan.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import com.dingfan.utils.ServiceGetter;

@WebService(name = "DingFanWebService", targetNamespace = "http://wwww.dingfan.org/webservice/DingFanWebService", portName = "DingFanWebServicePort", serviceName = "DingFanService")
public class DingFanWebServiceImpl implements DingFanWebService {

	
    @WebMethod(operationName = "AddOrder")
    @WebResult(name = "OrderResponse", partName = "addOrderResponse",targetNamespace="http://wwww.dingfan.org/DingFanWebService")
	public OrderResponse addOrder(@WebParam(name = "OrderRequest", partName = "addOrderRequest",
	targetNamespace="http://wwww.dingfan.org/webservice/DingFanWebService") OrderRequest orderRequest){
    	
		String personName = orderRequest.getPersonName();
		String amount = orderRequest.getAmount();
		String comments = orderRequest.getComments();
		
		ServiceGetter.getInstance().getDingFanService().saveOrders(personName, amount, comments, "0.0.0.0");
    	OrderResponse response = new OrderResponse();
    	response.setReturnCode("OK!");
    	return response;
    }
    
    public static void main(String[] args) {
    	Endpoint.publish("http://localhost:8989/webservice/DingFanService", new DingFanWebServiceImpl());
    	//wsimport . -keep http://localhost:8080/DingFan/webservice/?wsdl
	}
    
}
