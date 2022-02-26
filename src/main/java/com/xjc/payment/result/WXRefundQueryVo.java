package com.xjc.payment.result;

import java.io.Serializable;

/**
 * @Author jiachenxu
 * @Date 2022/2/26
 * @Descripetion
 */
public class WXRefundQueryVo implements Serializable {
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 商户退款单号
     */
    private String outRefundNo;
    /**
     * 订单总金额
     */
    private String totalFee;
    /**
     * 退款金额
     */
    private String refundFee;
    /**
     * 退款渠道
     * <p>
     * ORIGINAL—原路退款
     * <p>
     * BALANCE—退回到余额
     * <p>
     * OTHER_BALANCE—原账户异常退到其他余额账户
     * <p>
     * OTHER_BANKCARD—原银行卡异常退到其他银行卡
     */
    private String refundChanne;
    /**
     * 退款状态
     * <p>
     * SUCCESS—退款成功
     * <p>
     * REFUNDCLOSE—退款关闭，指商户发起退款失败的情况。
     * <p>
     * PROCESSING—退款处理中
     * <p>
     * CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。$n为下标，从0开始编号。
     */
    private String refundStatus;
    /**
     * 退款入账账户
     * <p>
     * 取当前退款单的退款入账方
     * 1）退回银行卡：
     * <p>
     * {银行名称}{卡类型}{卡尾号}
     * <p>
     * 2）退回支付用户零钱:
     * <p>
     * 支付用户零钱
     * <p>
     * 3）退还商户:
     * <p>
     * 商户基本账户
     * <p>
     * 商户结算银行账户
     * <p>
     * 4）退回支付用户零钱通:
     * <p>
     * 支付用户零钱通
     */
    private String refundRecvAccout;
    /**
     * 退款成功时间
     */
    private String refundSuccessTime;

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

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public String getRefundChanne() {
        return refundChanne;
    }

    public void setRefundChanne(String refundChanne) {
        this.refundChanne = refundChanne;
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

    public String getRefundSuccessTime() {
        return refundSuccessTime;
    }

    public void setRefundSuccessTime(String refundSuccessTime) {
        this.refundSuccessTime = refundSuccessTime;
    }
}
