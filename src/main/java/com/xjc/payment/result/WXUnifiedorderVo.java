package com.xjc.payment.result;

/**
 * @Author jiachenxu
 * @Date 2022/2/26
 * @Descripetion
 */
public class WXUnifiedorderVo {

    /**
     * 微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    private String prepayId;
    /**
     * 交易类型
     */
    private String tradeType;

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
}
