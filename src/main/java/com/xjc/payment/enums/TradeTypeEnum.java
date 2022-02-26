package com.xjc.payment.enums;



/**
 * @Author jiachenxu
 * @Date 2022/2/26
 * @Descripetion
 */
public enum TradeTypeEnum {

    JSAPI("JSAPI", "微信小程序支付"),

    NATIVE("NATIVE","微信本地支付"),

    APP("APP", "微信APP支付"),

    WAP("WAP","微信H5支付"),

    MWEB("MWEB","微信移动端");


    TradeTypeEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    private String value;

    private String text;



    public String getValue() {
        return value;
    }


    public String getText() {
        return text;
    }

}






