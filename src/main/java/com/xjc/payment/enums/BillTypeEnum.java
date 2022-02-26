package com.xjc.payment.enums;

/**
 * @Author jiachenxu
 * @Date 2022/2/26
 * @Descripetion
 */
public enum BillTypeEnum {

    ALL("ALL", "返回当日所有订单信息"),

    SUCCESS("SUCCESS", "返回当日成功支付的订单"),

    REFUND("REFUND", "返回当日退款订单"),

    REVOKED("REVOKED", "已撤销的订单");

    private String value;

    private String text;

    BillTypeEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }


    public String getText() {
        return text;
    }

}
