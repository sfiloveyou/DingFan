package com.dingfan.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(name = "DingFanWebService", targetNamespace = "http://wwww.dingfan.org/webservice/DingFanWebService", portName = "DingFanWebServicePort", serviceName = "DingFanService")
public interface DingFanWebService {

	
    @WebMethod(operationName = "AddOrder")
    @WebResult(name = "OrderResponse", partName = "addOrderResponse",targetNamespace="http://wwww.dingfan.org/webservice/DingFanWebService")
	public OrderResponse addOrder(@WebParam(name = "OrderRequest", partName = "addOrderRequest",
	targetNamespace="http://wwww.dingfan.org/webservice/DingFanWebService") OrderRequest orderRequest);
    
    
}
