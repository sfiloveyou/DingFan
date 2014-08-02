package com.dingfan.webservice.client;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderRequest" type="{http://wwww.dingfan.org/webservice/DingFanWebService}orderRequest" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddOrder", propOrder = {
    "orderRequest"
})
public class AddOrder {

    @XmlElement(name = "OrderRequest", namespace = "http://wwww.dingfan.org/webservice/DingFanWebService")
    protected OrderRequest orderRequest;

    /**
     * Gets the value of the orderRequest property.
     * 
     * @return
     *     possible object is
     *     {@link OrderRequest }
     *     
     */
    public OrderRequest getOrderRequest() {
        return orderRequest;
    }

    /**
     * Sets the value of the orderRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderRequest }
     *     
     */
    public void setOrderRequest(OrderRequest value) {
        this.orderRequest = value;
    }

}
