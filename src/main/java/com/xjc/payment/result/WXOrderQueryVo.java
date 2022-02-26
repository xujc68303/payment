package com.xjc.payment.result;

import java.io.Serializable;

/**
 * @Author jiachenxu
 * @Date 2022/2/26
 * @Descripetion
 */
public class WXOrderQueryVo implements Serializable {
    /**
     * 用户在商户appid下的唯一标识
     */
    private String openId;
    /**
     * 交易类型
     * 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY
     */
    private String tradeType;
    /**
     * 交易状态
     * SUCCESS--支付成功
     * REFUND--转入退款
     * NOTPAY--未支付
     * CLOSED--已关闭
     * REVOKED--已撤销(刷卡支付)
     * USERPAYING--用户支付中
     * PAYERROR--支付失败(其他原因，如银行返回失败)
     * ACCEPT--已接收，等待扣款
     */
    private String tradeState;
    /**
     * 银行类型，采用字符串类型的银行标识
     */
    private String bankType;
    /**
     * 总金额
     */
    private String totalFee;
    /**
     * 支付金额
     */
    private String cashFee;
    /**
     * 微信支付订单号
     */
    private String transactionId;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 完成时间
     * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    private String timeEnd;

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

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getCashFee() {
        return cashFee;
    }

    public void setCashFee(String cashFee) {
        this.cashFee = cashFee;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
