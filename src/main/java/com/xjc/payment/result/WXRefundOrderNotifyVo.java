package com.xjc.payment.result;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author jiachenxu
 * @Date 2022/2/27
 * @Descripetion
 */
public class WXRefundOrderNotifyVo implements Serializable {

    /**
     * 订单金额
     */
    private BigDecimal totalFee;
    /**
     * 支付金额
     */
    private BigDecimal settlementTotalFee;
    /**
     * 退款金额
     */
    private BigDecimal refundFee;
    /**
     * 退款金额
     */
    private BigDecimal settlementRefundFee;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 微信订单号
     */
    private String transactionId;
    /**
     * 微信退款id
     */
    private String refundId;
    /**
     * 商户退款单号
     */
    private String outRefundNo;
    /**
     * 完成时间
     */
    private String timeEnd;
    /**
     * 退款状态
     *
     * SUCCESS-退款成功
     * CHANGE-退款异常
     * REFUNDCLOSE—退款关闭
     */
    private String refundStatus;
    /**
     * 退款入账账户
     */
    private String refundRecvAccout;
    /**
     * 微信返回参数
     */
    private String wxResult;

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(BigDecimal settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public BigDecimal getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    public BigDecimal getSettlementRefundFee() {
        return settlementRefundFee;
    }

    public void setSettlementRefundFee(BigDecimal settlementRefundFee) {
        this.settlementRefundFee = settlementRefundFee;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundRecvAccout() {
        return refundRecvAccout;
    }

    public void setRefundRecvAccout(String refundRecvAccout) {
        this.refundRecvAccout = refundRecvAccout;
    }

    public String getWxResult() {
        return wxResult;
    }

    public void setWxResult(String wxResult) {
        this.wxResult = wxResult;
    }
}
