package com.xjc.payment.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author jiachenxu
 * @Date 2022/2/26
 * @Descripetion
 */
public class WXRefundOrderBean implements Serializable {

    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 商户退款单号
     */
    private String outRefundNo;
    /**
     * 订单金额
     */
    private BigDecimal totalFee;
    /**
     * 退款金额
     */
    private BigDecimal refundFee;
    /**
     * 退款原因
     */
    private String refundDesc;
    /**
     * 退款结果通知
     */
    private String notifyUrl;


    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    public String getRefundDesc() {
        return refundDesc;
    }

    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
