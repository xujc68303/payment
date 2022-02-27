package com.xjc.payment.result;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author jiachenxu
 * @Date 2022/2/27
 * @Descripetion
 */
public class WXPayOrderNotifyVo implements Serializable {

    /**
     * 用户在商户appid下的唯一标识
     */
    private String openId;
    /**
     * 交易类型
     */
    private String tradeType;
    /**
     * 付款银行
     */
    private String bankType;
    /**
     * 订单金额
     */
    private BigDecimal totalFee;
    /**
     * 支付金额
     */
    private BigDecimal settlementTotalFee;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 微信支付订单号
     */
    private String transactionId;
    /**
     * 支付完成时间
     */
    private String timeEnd;
    /**
     * 微信返回参数
     */
    private String wxResult;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

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

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getWxResult() {
        return wxResult;
    }

    public void setWxResult(String wxResult) {
        this.wxResult = wxResult;
    }
}
