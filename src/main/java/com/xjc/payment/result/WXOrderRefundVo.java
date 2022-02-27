package com.xjc.payment.result;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author jiachenxu
 * @Date 2022/2/26
 * @Descripetion
 */
public class WXOrderRefundVo implements Serializable {

    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 商户退款单号
     */
    private String outRefundNo;
    /**
     * 微信退款单号
     */
    private String refundId;
    /**
     * 退款金额
     */
    private BigDecimal refundFee;

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

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public BigDecimal getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }
}
