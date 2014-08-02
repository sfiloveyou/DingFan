
package com.dingfan.webservice.client;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "DingFanWebService", targetNamespace = "http://wwww.dingfan.org/webservice/DingFanWebService")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DingFanWebService {


    /**
     * 
     * @param orderRequest
     * @return
     *     returns org.dingfan.wwww.webservice.dingfanwebservice.OrderResponse
     */
    @WebMethod(operationName = "AddOrder")
    @WebResult(name = "OrderResponse", targetNamespace = "http://wwww.dingfan.org/webservice/DingFanWebService")
    @RequestWrapper(localName = "AddOrder", targetNamespace = "http://wwww.dingfan.org/webservice/DingFanWebService", className = "org.dingfan.wwww.webservice.dingfanwebservice.AddOrder")
    @ResponseWrapper(localName = "AddOrderResponse", targetNamespace = "http://wwww.dingfan.org/webservice/DingFanWebService", className = "org.dingfan.wwww.webservice.dingfanwebservice.AddOrderResponse")
    public OrderResponse addOrder(
        @WebParam(name = "OrderRequest", targetNamespace = "http://wwww.dingfan.org/webservice/DingFanWebService")
        OrderRequest orderRequest);

}