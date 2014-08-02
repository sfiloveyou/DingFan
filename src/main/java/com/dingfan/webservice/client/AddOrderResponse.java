
package com.dingfan.webservice.client;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddOrderResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddOrderResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderResponse" type="{http://wwww.dingfan.org/webservice/DingFanWebService}orderResponse" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddOrderResponse", propOrder = {
    "orderResponse"
})
public class AddOrderResponse {

    @XmlElement(name = "OrderResponse", namespace = "http://wwww.dingfan.org/webservice/DingFanWebService")
    protected OrderResponse orderResponse;

    /**
     * Gets the value of the orderResponse property.
     * 
     * @return
     *     possible object is
     *     {@link OrderResponse }
     *     
     */
    public OrderResponse getOrderResponse() {
        return orderResponse;
    }

    /**
     * Sets the value of the orderResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderResponse }
     *     
     */
    public void setOrderResponse(OrderResponse value) {
        this.orderResponse = value;
    }

}
