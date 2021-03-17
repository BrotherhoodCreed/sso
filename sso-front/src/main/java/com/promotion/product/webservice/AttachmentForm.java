
package com.promotion.product.webservice;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>attachmentForm complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="attachmentForm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fdKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fdFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fdAttachment" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attachmentForm", propOrder = {
    "fdKey",
    "fdFileName",
    "fdAttachment"
})
public class AttachmentForm {

    protected String fdKey;
    protected String fdFileName;
    @XmlMimeType("application/octet-stream")
    protected DataHandler fdAttachment;

    /**
     * ��ȡfdKey���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdKey() {
        return fdKey;
    }

    /**
     * ����fdKey���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdKey(String value) {
        this.fdKey = value;
    }

    /**
     * ��ȡfdFileName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdFileName() {
        return fdFileName;
    }

    /**
     * ����fdFileName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdFileName(String value) {
        this.fdFileName = value;
    }

    /**
     * ��ȡfdAttachment���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getFdAttachment() {
        return fdAttachment;
    }

    /**
     * ����fdAttachment���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setFdAttachment(DataHandler value) {
        this.fdAttachment = value;
    }

}
