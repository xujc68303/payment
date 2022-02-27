package com.xjc.payment.bean;

import com.xjc.payment.enums.TradeTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author jiachenxu
 * @Date 2022/2/26
 * @Descripetion
 */
public class WXUnifiedOrderBean implements Serializable {

    /**
     * 商品描述
     */
    private String body;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 标价金额(单位为分)
     */
    private BigDecimal totalFee;
    /**
     * 终端IP
     */
    private String spbillCreateIp;
    /**
     * 回调地址
     */
    private String notifyUrl;
    /**
     * 交易类型
     * @see TradeTypeEnum
     */
    private String tradeType;
    /**
     * 用户标识
     */
    private String openId;
    /**
     * 商品ID
     */
    private String productId;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
